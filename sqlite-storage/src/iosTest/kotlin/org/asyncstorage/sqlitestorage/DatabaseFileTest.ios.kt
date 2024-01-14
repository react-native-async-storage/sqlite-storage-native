@file:OptIn(ExperimentalNativeApi::class)

package org.asyncstorage.sqlitestorage

import org.asyncstorage.sqlitestorage.utils.FileTestHelper
import kotlin.experimental.ExperimentalNativeApi
import kotlin.test.Test

internal actual fun createDatabaseFile(name: String): DatabaseFiles {
    return IosDatabaseFile(name)
}

class IosDbUtilsTest {
    @Test
    fun ios_database_is_located_in_Application_Support() {
        val dbName = "test-db"
        val file = createDatabaseFile(dbName)
        FileTestHelper.createFileAt(file.path())
        val parentDir = "/data/Library/Application Support/SqliteStorage/databases"

        with(file.path()) {
            assert(this.endsWith("$parentDir/$dbName")) {
                "db path not matching, expected: $parentDir/$dbName, received: $this"
            }
        }

        with(file.path(DatabaseFileType.Wal)) {
            assert(this.endsWith("$parentDir/$dbName-wal")) {
                "db path not matching, expected: $parentDir/$dbName-wal, received: $this"
            }
        }

        with(file.path(DatabaseFileType.Index)) {
            assert(this.endsWith("$parentDir/$dbName-shm")) {
                "db path not matching, expected: $parentDir/$dbName-shm, received: $this"
            }
        }
    }
}
