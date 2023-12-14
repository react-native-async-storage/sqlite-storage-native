package org.asyncstorage.sqlitestorage

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.asyncstorage.sqlitestorage.db.AsyncStorageDB
import org.asyncstorage.sqlitestorage.dispatchers.DispatcherIO
import org.asyncstorage.sqlitestorage.utils.DatabaseUtils

actual class SQLiteStorageFactory(private val ctx: Context) {
    actual fun create(dbName: String): SqliteStorage {
        val driver = AndroidSqliteDriver(AsyncStorageDB.Schema, ctx, dbName)
        val dbUtils = DatabaseUtils(dbName, ctx)
        return DefaultSqliteStorage(
            driver = driver,
            dbUtils = dbUtils,
            readDispatcher = DispatcherIO.limitedParallelism(3),
            writeDispatcher = DispatcherIO.limitedParallelism(1)
        )
    }
}
