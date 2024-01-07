@file:OptIn(ExperimentalNativeApi::class)

package org.asyncstorage.sqlitestorage

import org.asyncstorage.sqlitestorage.utils.FileTestHelper
import kotlin.experimental.ExperimentalNativeApi
import kotlin.test.Test

internal actual fun createDatabaseFile(name: String): DatabaseFile {
    return IosDatabaseFile(name)
}

class IosDbUtilsTest {
    @Test
    fun ios_database_is_located_in_Application_Support() {
        val file = createDatabaseFile("test-db")
        val dbPath = file.path()
        FileTestHelper.createFileAt(dbPath)
        val expectedPath = "/data/Library/Application Support/databases/test-db"
        assert(dbPath.endsWith("/data/Library/Application Support/SqliteStorage/databases/test-db")) {
            "db path not matching, expected: $expectedPath, received: $dbPath"
        }
    }
}
