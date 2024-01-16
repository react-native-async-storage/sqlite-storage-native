package org.asyncstorage.sqlitestorage.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import kotlinx.coroutines.CoroutineDispatcher
import org.asyncstorage.sqlitestorage.AndroidDatabaseFile
import org.asyncstorage.sqlitestorage.DefaultSQLiteStorage
import org.asyncstorage.sqlitestorage.SQLiteStorage
import org.asyncstorage.sqlitestorage.db.AsyncStorageDB

actual fun createTestDatabase(
    dbName: String,
    dispatcher: CoroutineDispatcher,
): SQLiteStorage {
    val ctx = ApplicationProvider.getApplicationContext<Context>()
    // passing name as null to SupportSQLiteOpenHelper creates in-memory db
    val driver = AndroidSqliteDriver(AsyncStorageDB.Schema, ctx, null)
    val dbUtils = AndroidDatabaseFile(ctx.getDatabasePath(dbName))
    return DefaultSQLiteStorage(driver, dbUtils, dispatcher)
}
