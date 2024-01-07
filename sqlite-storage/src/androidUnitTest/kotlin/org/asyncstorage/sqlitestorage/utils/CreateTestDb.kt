package org.asyncstorage.sqlitestorage.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import kotlinx.coroutines.CoroutineDispatcher
import org.asyncstorage.sqlitestorage.AndroidDatabaseFile
import org.asyncstorage.sqlitestorage.DefaultSqliteStorage
import org.asyncstorage.sqlitestorage.SqliteStorage
import org.asyncstorage.sqlitestorage.db.AsyncStorageDB

actual fun createTestDatabase(
    dbName: String,
    dispatcher: CoroutineDispatcher,
): SqliteStorage {
    val ctx = ApplicationProvider.getApplicationContext<Context>()
    // passing name as null to SupportSQLiteOpenHelper creates in-memory db
    val driver = AndroidSqliteDriver(AsyncStorageDB.Schema, ctx, null)
    val dbUtils = AndroidDatabaseFile(ctx.getDatabasePath(dbName))
    return DefaultSqliteStorage(driver, dbUtils, dispatcher)
}
