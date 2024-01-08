package org.asyncstorage.sqlite


import android.content.Context
import com.facebook.react.bridge.Promise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.asyncstorage.sqlitestorage.SqliteStorage
import org.asyncstorage.sqlitestorage.SqliteStorageFactory
import org.asyncstorage.sqlitestorage.models.Entry
import org.asyncstorage.sqlitestorage.models.Key
import java.util.concurrent.ConcurrentHashMap


class AsyncStorageSqlite(ctx: Context) {
    private val storages: ConcurrentHashMap<String, SqliteStorage> = ConcurrentHashMap()
    private val factory = SqliteStorageFactory(ctx)
    private val scope = CoroutineScope(Dispatchers.Default)


    fun read(dbName: String, keys: List<String>, promise: Promise) = withStorage(dbName, promise) {
        if (keys.isEmpty()) {
            return@withStorage false
        }
        val result = readMany(keys)
        result.toReadableMap()
    }

    fun store(dbName: String, values: List<Entry>, promise: Promise) =
        withStorage(dbName, promise) {
            if (values.isEmpty()) {
                return@withStorage false
            }
            writeMany(values)
            true
        }

    fun delete(dbName: String, keys: List<Key>, promise: Promise) = withStorage(dbName, promise) {
        if (keys.isEmpty()) {
            return@withStorage false
        }
        removeMany(keys)
        true
    }

    fun merge(dbName: String, entries: List<Entry>, promise: Promise) =
        withStorage(dbName, promise) {
            val result = mergeMany(entries)
            result.toReadableMap()
        }

    fun drop(dbName: String, promise: Promise) = withStorage(dbName, promise) {
        dropStorage(dbName)
    }

    fun keys(dbName: String, promise: Promise) = withStorage(dbName, promise) {
        getKeys().toReadableArray()
    }

    fun absolutePath(dbName: String, promise: Promise) = withStorage(dbName, promise) {
        getDbPath()
    }

    fun fileSize(dbName: String, promise: Promise) = withStorage(dbName, promise) {
        val size = getDbSize()
        size.toInt()
    }


    private fun <R> withStorage(
        dbName: String,
        promise: Promise,
        block: suspend SqliteStorage.() -> R
    ) {
        scope.launch {
            val storage = storages.getOrPut(dbName) { factory.create(dbName) }
            val result = storage.block()
            promise.resolve(result)
        }
    }

    private suspend fun dropStorage(dbName: String) {
        storages.getOrDefault(dbName, null)?.let {
            it.closeConnection()
            storages.remove(dbName)
        }
    }

    companion object {
        const val NAME = "AsyncStorageSqlite"
    }
}
