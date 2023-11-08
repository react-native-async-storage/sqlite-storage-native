package org.asyncstorage.sqlitestorage.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.squareup.sqldelight.android.AndroidSqliteDriver
import kotlinx.coroutines.CoroutineDispatcher
import org.asyncstorage.sqlitestorage.SQLiteStorage
import org.asyncstorage.sqlitestorage.StorageAccess
import org.asyncstorage.sqlitestorage.db.StorageDB

actual fun createTestDatabase(
    dbName: String,
    dispatcher: CoroutineDispatcher,
    inMemory: Boolean,
): StorageAccess {
    val ctx = ApplicationProvider.getApplicationContext<Context>()

    // passing name as null to SupportSQLiteOpenHelper creates in-memory db
    val realDbName = if (inMemory) null else dbName
    val driver = AndroidSqliteDriver(StorageDB.Schema, ctx, realDbName)
    val dbUtils = org.asyncstorage.sqlitestorage.utils.DatabaseUtils(dbName, ctx)
    return SQLiteStorage(driver, dbUtils, dispatcher)
}
