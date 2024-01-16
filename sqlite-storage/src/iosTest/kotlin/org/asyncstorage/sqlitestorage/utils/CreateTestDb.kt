package org.asyncstorage.sqlitestorage.utils

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection
import co.touchlab.sqliter.DatabaseConfiguration
import kotlinx.coroutines.CoroutineDispatcher
import org.asyncstorage.sqlitestorage.DefaultSQLiteStorage
import org.asyncstorage.sqlitestorage.IosDatabaseFile
import org.asyncstorage.sqlitestorage.SQLiteStorage
import org.asyncstorage.sqlitestorage.db.AsyncStorageDB

actual fun createTestDatabase(
    dbName: String,
    dispatcher: CoroutineDispatcher,
): SQLiteStorage {
    val config =
        DatabaseConfiguration(
            name = dbName,
            version = AsyncStorageDB.Schema.version.toInt(),
            create = { connection ->
                wrapConnection(connection) { AsyncStorageDB.Schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) {
                    AsyncStorageDB.Schema.migrate(
                        it,
                        oldVersion.toLong(),
                        newVersion.toLong(),
                    )
                }
            },
            inMemory = true,
        )
    val driver = NativeSqliteDriver(configuration = config)
    val dbFile = IosDatabaseFile(dbName)
    return DefaultSQLiteStorage(driver, dbFile, dispatcher)
}
