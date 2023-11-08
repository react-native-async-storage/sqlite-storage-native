package org.asyncstorage.sqlitestorage

import co.touchlab.sqliter.DatabaseConfiguration
import co.touchlab.sqliter.JournalMode
import co.touchlab.sqliter.SynchronousFlag
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection
import org.asyncstorage.sqlitestorage.db.StorageDB
import org.asyncstorage.sqlitestorage.dispatchers.DispatcherIO
import org.asyncstorage.sqlitestorage.utils.DatabaseUtils

actual class SQLiteStorageFactory {
    actual fun create(dbName: String): StorageAccess {
        val dbUtils = DatabaseUtils(dbName)
        dbUtils.createBaseDirectoryIfNotExisting()
        val config =
            DatabaseConfiguration(
                name = dbName,
                version = StorageDB.Schema.version,
                journalMode = JournalMode.WAL,
                extendedConfig =
                    DatabaseConfiguration.Extended()
                        .copy(
                            synchronousFlag = SynchronousFlag.NORMAL,
                            basePath = dbUtils.getDbBasePath(),
                        ),
                create = { connection ->
                    wrapConnection(connection) { StorageDB.Schema.create(it) }
                },
                upgrade = { connection, oldVersion, newVersion ->
                    wrapConnection(connection) {
                        StorageDB.Schema.migrate(
                            it,
                            oldVersion,
                            newVersion,
                        )
                    }
                },
            )
        val driver = NativeSqliteDriver(config)
        return SQLiteStorage(driver, dbUtils, DispatcherIO)
    }
}
