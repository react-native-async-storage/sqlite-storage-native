package org.asyncstorage.sqlitestorage

import org.asyncstorage.sqlitestorage.utils.DatabaseUtils
import org.asyncstorage.sqlitestorage.utils.FileTestHelper
import org.asyncstorage.sqlitestorage.utils.JunitRunner
import org.asyncstorage.sqlitestorage.utils.RunWith
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal expect fun createUtils(name: String = "Test"): DatabaseUtils

@RunWith(JunitRunner::class)
class CommonDbUtilsTest {
    @Test
    fun correct_database_path_is_returned() {
        val dbName = "testing_my_db.db"
        val utils = createUtils(dbName)
        val dbPath = utils.getDbFilePath()
        assertTrue(dbPath.endsWith("databases/$dbName"), "db path not matching $dbPath")
    }

    @Test
    fun returns_db_file_size() {
        val dbName = "testing.db"
        val utils = createUtils(dbName)
        val helper = FileTestHelper.createFileAt(utils.getDbFilePath())
        assertTrue(helper.exists(), "db file not created")
        helper.fillWithChars(2048)
        val size = utils.getDbFileSize()
        assertTrue(size == 2L, "size not matching $size")
    }

    @Test
    fun returns_negative_one_size_if_db_file_does_not_exist() {
        val utils = createUtils("sizing.db")
        val helper = FileTestHelper.createFileAt(utils.getDbFilePath())
        assertTrue(helper.exists(), "db file should exists")
        utils.removeDbFiles()
        assertTrue(!helper.exists(), "db file still exists")
        val size = utils.getDbFileSize()
        assertEquals(-1L, size, "size should be -1, instead: $size")
    }
}
