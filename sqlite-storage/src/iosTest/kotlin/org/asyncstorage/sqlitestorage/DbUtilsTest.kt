@file:OptIn(ExperimentalNativeApi::class)

package org.asyncstorage.sqlitestorage

import org.asyncstorage.sqlitestorage.utils.DatabaseUtils
import org.asyncstorage.sqlitestorage.utils.FileTestHelper
import kotlin.experimental.ExperimentalNativeApi
import kotlin.test.Test

internal actual fun createUtils(name: String): DatabaseUtils {
    return DatabaseUtils(name)
}

class IosDbUtilsTest {
    @Test
    fun ios_database_is_located_in_Application_Support() {
        val utils = DatabaseUtils("test-db")
        val dbPath = utils.getDbFilePath()
        FileTestHelper.createFileAt(dbPath)
        val expectedPath = "/data/Library/Application Support/databases/test-db"
        assert(dbPath.endsWith("/data/Library/Application Support/SqliteStorage/databases/test-db")) {
            "db path not matching, expected: $expectedPath, received: $dbPath"
        }
    }
}
