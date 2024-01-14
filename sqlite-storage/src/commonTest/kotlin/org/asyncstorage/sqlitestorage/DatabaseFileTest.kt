package org.asyncstorage.sqlitestorage

import org.asyncstorage.sqlitestorage.utils.FileTestHelper
import org.asyncstorage.sqlitestorage.utils.JunitRunner
import org.asyncstorage.sqlitestorage.utils.RunWith
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal expect fun createDatabaseFile(name: String = "Test"): DatabaseFiles

@RunWith(JunitRunner::class)
class DatabaseFileTest {
    @Test
    fun correct_database_path_is_returned() {
        val dbName = "testing_my_db.db"
        val file = createDatabaseFile(dbName)
        val dbPath = file.dirPath()
        assertTrue(file.dirPath().endsWith("/databases"), "db path not matching $dbPath")
    }

    @Test
    fun returns_db_file_size() {
        val dbName = "testing.db"
        val file = createDatabaseFile(dbName)
        val helper = FileTestHelper.createFileAt(file.path())
        assertTrue(helper.exists(), "db file not created")
        helper.fillWithChars(2048)
        val size = file.size()
        assertTrue(size == 2L, "size not matching $size")
    }

    @Test
    fun returns_negative_one_size_if_db_file_does_not_exist() {
        val file = createDatabaseFile("sizing.db")
        val helper = FileTestHelper.createFileAt(file.path())
        assertTrue(helper.exists(), "db file should exists")
        file.delete()
        assertTrue(!helper.exists(), "db file still exists")
        val size = file.size()
        assertEquals(null, size, "size should be null, instead: $size")
    }
}
