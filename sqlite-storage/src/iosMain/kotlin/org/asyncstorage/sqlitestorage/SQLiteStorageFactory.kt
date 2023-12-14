package org.asyncstorage.sqlitestorage

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection
import co.touchlab.sqliter.DatabaseConfiguration
import co.touchlab.sqliter.JournalMode
import co.touchlab.sqliter.SynchronousFlag
import org.asyncstorage.sqlitestorage.db.AsyncStorageDB
import org.asyncstorage.sqlitestorage.dispatchers.DispatcherIO
import org.asyncstorage.sqlitestorage.utils.DatabaseUtils

actual class SQLiteStorageFactory {
    actual fun create(dbName: String): StorageAccess {
        val dbUtils = DatabaseUtils(dbName)
        dbUtils.createBaseDirectoryIfNotExisting()
        val config =
            DatabaseConfiguration(
                name = dbName,
                version = AsyncStorageDB.Schema.version.toInt(),
                journalMode = JournalMode.WAL,
                extendedConfig =
                DatabaseConfiguration.Extended()
                    .copy(
                        synchronousFlag = SynchronousFlag.NORMAL,
                        basePath = dbUtils.getDbBasePath(),
                    ),
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
            )
        val driver = NativeSqliteDriver(config)
        return SQLiteStorage(driver, dbUtils, DispatcherIO)
    }
}
