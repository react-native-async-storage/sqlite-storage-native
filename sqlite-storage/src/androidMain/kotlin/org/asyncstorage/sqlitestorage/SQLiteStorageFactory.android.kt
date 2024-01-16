package org.asyncstorage.sqlitestorage

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.asyncstorage.sqlitestorage.db.AsyncStorageDB

actual class SQLiteStorageFactory(private val ctx: Context) {
    actual fun create(dbName: String): SQLiteStorage {
        val driver = createDriver(dbName)
        val dbUtils = AndroidDatabaseFile(ctx.getDatabasePath(dbName))
        return SQLiteStorage(
            driver = driver,
            dbFile = dbUtils,
        )
    }

    private fun createDriver(dbName: String): AndroidSqliteDriver {
        val callback =
            object : AndroidSqliteDriver.Callback(AsyncStorageDB.Schema) {
                override fun onConfigure(db: SupportSQLiteDatabase) {
                    /**
                     * Makes this database work in Write-ahead log journal mode
                     * Available for SQLite version 3.7.0 (2010-07-21) or later
                     * Android supports 3.7.0+ since API 11 (https://developer.android.com/reference/android/database/sqlite/package-summary)
                     */
                    db.enableWriteAheadLogging()
                    /**
                     * Makes sqlite use Normal mode for synchronizing DB content with filesystem only in crucial times
                     * WAL file is synchronized before each checkpoint and the database file is synchronized after each completed checkpoint
                     *
                     * https://sqlite.org/pragma.html#pragma_synchronous
                     */
                    db.execSQL("PRAGMA synchronous = NORMAL")
                }
            }

        return AndroidSqliteDriver(
            schema = AsyncStorageDB.Schema,
            context = ctx,
            name = dbName,
            callback = callback,
        )
    }
}
