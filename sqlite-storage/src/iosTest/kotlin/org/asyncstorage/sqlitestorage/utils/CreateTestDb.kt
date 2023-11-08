package org.asyncstorage.sqlitestorage.utils

import co.touchlab.sqliter.DatabaseConfiguration
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection
import kotlinx.coroutines.CoroutineDispatcher
import org.asyncstorage.sqlitestorage.SQLiteStorage
import org.asyncstorage.sqlitestorage.StorageAccess
import org.asyncstorage.sqlitestorage.db.StorageDB

actual fun createTestDatabase(
    dbName: String,
    dispatcher: CoroutineDispatcher,
    inMemory: Boolean,
): StorageAccess {
    val config =
        DatabaseConfiguration(
            name = dbName,
            version = StorageDB.Schema.version,
            create = { connection ->
                wrapConnection(connection) { StorageDB.Schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { StorageDB.Schema.migrate(it, oldVersion, newVersion) }
            },
            inMemory = inMemory,
        )
    val driver = NativeSqliteDriver(configuration = config)
    val dbUtils = DatabaseUtils(dbName)
    return SQLiteStorage(driver, dbUtils, dispatcher)
}
