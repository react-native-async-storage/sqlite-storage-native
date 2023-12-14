package org.asyncstorage.sqlitestorage.utils

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection
import co.touchlab.sqliter.DatabaseConfiguration
import kotlinx.coroutines.CoroutineDispatcher
import org.asyncstorage.sqlitestorage.DefaultSqliteStorage
import org.asyncstorage.sqlitestorage.SqliteStorage
import org.asyncstorage.sqlitestorage.db.AsyncStorageDB

actual fun createTestDatabase(
    dbName: String,
    dispatcher: CoroutineDispatcher,
): SqliteStorage {
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
    val dbUtils = DatabaseUtils(dbName)
    return DefaultSqliteStorage(driver, dbUtils, dispatcher, dispatcher)
}
