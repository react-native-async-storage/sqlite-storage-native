package org.asyncstorage.sqlite

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.module.annotations.ReactModule

@ReactModule(name = AsyncStorageSqlite.NAME)
class AsyncStorageSqliteModule(ctx: ReactApplicationContext) : ReactContextBaseJavaModule(ctx) {
    override fun getName() = AsyncStorageSqlite.NAME

    private val storage = AsyncStorageSqlite(ctx)

    @ReactMethod
    fun readMany(dbName: String, keyArg: ReadableArray, promise: Promise) {
        val keys = keyArg.toKeyList()
        storage.read(dbName, keys, promise)
    }

    @ReactMethod
    fun saveMany(dbName: String, entryArg: ReadableMap, promise: Promise) {
        val entries = entryArg.toEntryList()
        storage.store(dbName, entries, promise)
    }

    @ReactMethod
    fun deleteMany(dbName: String, keyArg: ReadableArray, promise: Promise) {
        val keys = keyArg.toKeyList()
        storage.delete(dbName, keys, promise)
    }

    @ReactMethod
    fun merge(dbName: String, entryArg: ReadableMap, promise: Promise) {
        val entries = entryArg.toEntryList()
        storage.merge(dbName, entries, promise)
    }

    @ReactMethod
    fun getKeys(dbName: String, promise: Promise) {
        storage.keys(dbName, promise)
    }

    @ReactMethod
    fun dropStorage(dbName: String, promise: Promise) {
        storage.drop(dbName, promise)
    }

    @ReactMethod
    fun getDatabasePath(dbName: String, promise: Promise) {
        storage.absolutePath(dbName, promise)
    }

    @ReactMethod
    fun getDatabaseSize(dbName: String, promise: Promise) {
        storage.fileSize(dbName, promise)
    }
}

