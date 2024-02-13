package org.asyncstorage.sqlitestorage

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection
import co.touchlab.sqliter.DatabaseConfiguration
import co.touchlab.sqliter.JournalMode
import co.touchlab.sqliter.SynchronousFlag
import org.asyncstorage.sqlitestorage.db.AsyncStorageDB

@ObjCName(name = "SQLiteStorageFactoryKMP", swiftName = "SQLiteStorageFactoryKMP")
actual class SQLiteStorageFactory {
    actual fun create(dbName: String): SQLiteStorage {
        val dbFile =
            IosDatabaseFile(dbName).apply {
                createBaseDirectoryIfNotExisting()
            }
        val driver = createDriver(dbName, dbFile.dirPath())
        return SQLiteStorage(
            driver = driver,
            dbFile = dbFile,
        )
    }

    private fun createDriver(
        dbName: String,
        dbDirectory: String,
    ): NativeSqliteDriver {
        val config =
            DatabaseConfiguration(
                name = dbName,
                version = AsyncStorageDB.Schema.version.toInt(),
                journalMode = JournalMode.WAL,
                extendedConfig =
                    DatabaseConfiguration.Extended()
                        .copy(
                            synchronousFlag = SynchronousFlag.NORMAL,
                            basePath = dbDirectory,
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

        return NativeSqliteDriver(config)
    }
}
