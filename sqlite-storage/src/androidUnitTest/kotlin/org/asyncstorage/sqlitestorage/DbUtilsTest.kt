package org.asyncstorage.sqlitestorage

import androidx.test.core.app.ApplicationProvider
import org.asyncstorage.sqlitestorage.utils.DatabaseUtils
import org.asyncstorage.sqlitestorage.utils.JunitRunner
import org.junit.Test
import org.junit.runner.RunWith

internal actual fun createUtils(name: String): DatabaseUtils {
    return DatabaseUtils(name, ApplicationProvider.getApplicationContext())
}

@RunWith(JunitRunner::class)
class AndroidDbUtilsTest {
    @Test
    fun `correct database path is returned`() {
        val dbName = "testing_my_db.db"
        val utils = createUtils(dbName)
        val dbPath = utils.getDbFilePath()
        val expected = "databases/$dbName"
        assert(dbPath.endsWith("databases/$dbName")) {
            "db path not matching, expected: $expected, received: $dbPath"
        }
    }
}
