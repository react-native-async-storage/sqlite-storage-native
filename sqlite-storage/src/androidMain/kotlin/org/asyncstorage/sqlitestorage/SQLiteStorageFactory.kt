package org.asyncstorage.sqlitestorage

import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.asyncstorage.sqlitestorage.db.AsyncStorageDB
import org.asyncstorage.sqlitestorage.dispatchers.DispatcherIO
import org.asyncstorage.sqlitestorage.models.AppContext
import org.asyncstorage.sqlitestorage.utils.DatabaseUtils

actual class SQLiteStorageFactory(private val ctx: AppContext) {
    actual fun create(dbName: String): StorageAccess {
        val driver = AndroidSqliteDriver(AsyncStorageDB.Schema, ctx, dbName)
        val dbUtils = DatabaseUtils(dbName, ctx)
        return SQLiteStorage(driver, dbUtils, DispatcherIO)
    }
}
