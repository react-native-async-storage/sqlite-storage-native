package org.asyncstorage.sqlitestorage

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.asyncstorage.sqlitestorage.db.AsyncStorageDB
import org.asyncstorage.sqlitestorage.dispatchers.DispatcherIO
import org.asyncstorage.sqlitestorage.extensions.executePragmaOptimize
import org.asyncstorage.sqlitestorage.models.Entry
import org.asyncstorage.sqlitestorage.models.Key
import org.asyncstorage.sqlitestorage.utils.mergePossibleJsonValues

internal class DefaultSqliteStorage(
    private val driver: SqlDriver,
    override val files: DatabaseFiles,
    private val dispatcher: CoroutineDispatcher = DispatcherIO,
) : SqliteStorage {
    private val queries = AsyncStorageDB(driver).async_storage_entriesQueries

    override suspend fun read(key: Key): Entry =
        withContext(dispatcher) {
            queries.getOne(key, ::Entry).executeAsOneOrNull() ?: Entry(key)
        }

    override suspend fun readMany(keys: List<Key>): List<Entry> =
        withContext(dispatcher) {
            val found = queries.getMany(keys, ::Entry).executeAsList()
            keys.map { key ->
                found.find { it.key == key } ?: Entry(key)
            }
        }

    override fun readAsFlow(keys: List<Key>): Flow<List<Entry>> {
        return queries.getMany(keys, ::Entry)
            .asFlow()
            .mapToList(dispatcher)
    }

    override suspend fun write(entry: Entry) =
        withContext(dispatcher) {
            queries.insertOne(entry.key, entry.value)
        }

    override suspend fun writeMany(entries: List<Entry>) =
        withContext(dispatcher) {
            queries.transaction {
                entries.forEach { entry ->
                    queries.insertOne(entry.key, entry.value)
                }
            }
        }

    override suspend fun remove(key: Key) =
        withContext(dispatcher) {
            queries.deleteOne(key)
        }

    override suspend fun removeMany(keys: List<Key>) =
        withContext(dispatcher) {
            queries.deleteMany(keys)
        }


    override suspend fun merge(entry: Entry) =
        withContext(dispatcher) {
            queries.transactionWithResult {
                val current = queries.getOne(entry.key).executeAsOneOrNull()
                if (current == null) {
                    queries.insertOne(entry.key, entry.value)
                    return@transactionWithResult entry
                } else {
                    val merged = mergePossibleJsonValues(current.value_, entry.value)
                    queries.insertOne(entry.key, merged)
                    return@transactionWithResult Entry(entry.key, merged)
                }
            }
        }

    override suspend fun mergeMany(entries: List<Entry>) =
        withContext<List<Entry>>(dispatcher) {
            queries.transactionWithResult {
                val result = mutableListOf<Entry>()
                for (entry in entries) {
                    val current = queries.getOne(entry.key).executeAsOneOrNull()
                    if (current == null) {
                        queries.insertOne(entry.key, entry.value)
                        result.add(entry)
                    } else {
                        val merged = mergePossibleJsonValues(current.value_, entry.value)
                        queries.insertOne(entry.key, merged)
                        result.add(Entry(entry.key, merged))
                    }
                }
                return@transactionWithResult result
            }
        }

    override suspend fun readKeys(): List<Key> =
        withContext(dispatcher) {
            queries.getAllKeys().executeAsList()
        }

    override fun readKeysAsFlow(): Flow<List<Key>> {
        return queries.getAllKeys()
            .asFlow()
            .map { q ->
                q.executeAsList()
            }
    }

    override suspend fun clear() =
        withContext(dispatcher) {
            queries.deleteAll()
        }

    override suspend fun closeConnection() =
        withContext(dispatcher) {
            driver.executePragmaOptimize()
            driver.close()
        }
}
