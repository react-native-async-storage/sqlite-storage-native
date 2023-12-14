package org.asyncstorage.sqlitestorage

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.asyncstorage.sqlitestorage.db.AsyncStorageDB
import org.asyncstorage.sqlitestorage.extensions.executePragmaOptimize
import org.asyncstorage.sqlitestorage.extensions.executePragmaSyncNormal
import org.asyncstorage.sqlitestorage.extensions.executePragmaWalCheckpoint
import org.asyncstorage.sqlitestorage.extensions.executePragmaWalJournalMode
import org.asyncstorage.sqlitestorage.models.Entry
import org.asyncstorage.sqlitestorage.models.Key
import org.asyncstorage.sqlitestorage.utils.DatabaseUtils
import org.asyncstorage.sqlitestorage.utils.mergePossibleJsonValues

internal class DefaultSqliteStorage(
    private val driver: SqlDriver,
    private val dbUtils: DatabaseUtils,
    private val readDispatcher: CoroutineDispatcher,
    private val writeDispatcher: CoroutineDispatcher
) : SqliteStorage {
    private val queries = AsyncStorageDB(driver).async_storage_entriesQueries

    init {
        /**
         * Makes this database work in Write-ahead log journal mode
         * Available for SQLite version 3.7.0 (2010-07-21) or later
         * Android supports 3.7.0+ since API 11 (https://developer.android.com/reference/android/database/sqlite/package-summary)
         * iOS supports 3.7.0+ since iOS 4.3.x (https://www.theiphonewiki.com/wiki/Databases)
         */
        driver.executePragmaWalJournalMode()
        driver.executePragmaSyncNormal()
    }

    override suspend fun read(key: Key): Entry =
        withContext(readDispatcher) {
            queries.getOne(key) { k, v -> Entry(k, v) }.executeAsOneOrNull() ?: Entry(key)
        }

    override fun readAsFlow(key: Key): Flow<Entry> {
        return queries.getOne(key) { k, v -> Entry(k, v) }
            .asFlow()
            .map { q ->
                q.executeAsOneOrNull() ?: Entry(key)
            }
    }

    override suspend fun write(entry: Entry) =
        withContext(writeDispatcher) {
            queries.insertOne(entry.key, entry.value)
        }

    override suspend fun merge(entry: Entry) =
        withContext(writeDispatcher) {
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

    override suspend fun remove(key: Key) =
        withContext(writeDispatcher) {
            queries.deleteOne(key)
        }

    override suspend fun readMany(keys: List<Key>): List<Entry> =
        withContext(readDispatcher) {
            val found = queries.getMany(keys) { k, v -> Entry(k, v) }.executeAsList()
            keys.map { key ->
                found.find { it.key == key } ?: Entry(key)
            }
        }

    override fun readManyAsFlow(keys: List<Key>): Flow<List<Entry>> {
        return queries.getMany(keys) { k, v -> Entry(k, v) }
            .asFlow()
            .map { q ->
                val found = q.executeAsList()
                keys.map { key ->
                    found.find { it.key == key } ?: Entry(key)
                }
            }
    }

    override suspend fun writeMany(entries: List<Entry>) =
        withContext(writeDispatcher) {
            queries.transaction {
                entries.forEach { entry ->
                    queries.insertOne(entry.key, entry.value)
                }
            }
        }

    override suspend fun mergeMany(entries: List<Entry>) =
        withContext<List<Entry>>(writeDispatcher) {
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

    override suspend fun removeMany(keys: List<Key>) =
        withContext(writeDispatcher) {
            queries.deleteMany(keys)
        }

    override suspend fun clear() =
        withContext(writeDispatcher) {
            queries.deleteAll()
        }

    override suspend fun getKeys(): List<Key> =
        withContext(readDispatcher) {
            queries.getAllKeys().executeAsList()
        }

    override fun getKeysAsFlow(): Flow<List<Key>> {
        return queries.getAllKeys()
            .asFlow()
            .map { q ->
                q.executeAsList()
            }
    }

    override suspend fun closeConnection() = withContext(writeDispatcher) {
        driver.executePragmaOptimize()
        driver.close()
    }

    override fun getDbPath() = dbUtils.getDbFilePath()

    override suspend fun getDbSize(): Long =
        withContext(writeDispatcher) {
            driver.executePragmaWalCheckpoint()
            dbUtils.getDbFileSize()
        }

    override suspend fun dropDatabase() =
        withContext(writeDispatcher) {
            driver.close()
            dbUtils.removeDbFiles()
        }
}
