package org.asyncstorage.sqlitestorage

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.asyncstorage.sqlitestorage.utils.JunitRunner
import org.junit.Test
import org.junit.runner.RunWith

internal actual fun createDatabaseFile(name: String): DatabaseFile {
    val ctx: Context = ApplicationProvider.getApplicationContext()
    return AndroidDatabaseFile(ctx.getDatabasePath(name))
}

@RunWith(JunitRunner::class)
class AndroidDatabaseFileTest {
    @Test
    fun `correct database path is returned`() {
        val dbName = "testing_my_db.db"
        val file = createDatabaseFile(dbName)
        val dbPath = file.path()
        val expected = "databases/$dbName"
        assert(dbPath.endsWith("databases/$dbName")) {
            "db path not matching, expected: $expected, received: $dbPath"
        }
    }
}
