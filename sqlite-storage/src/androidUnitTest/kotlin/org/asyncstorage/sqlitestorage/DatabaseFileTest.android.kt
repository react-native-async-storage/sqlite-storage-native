package org.asyncstorage.sqlitestorage

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.asyncstorage.sqlitestorage.utils.JunitRunner
import org.junit.Test
import org.junit.runner.RunWith

internal actual fun createDatabaseFile(name: String): DatabaseFiles {
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

        with(file.path()) {
            assert(this.endsWith(dbName)) {
                "db path not matching, expected: $dbName, received: $this"
            }
        }

        with(file.path(DatabaseFileType.Index)) {
            assert(this.endsWith("$dbName-shm")) {
                "index path not matching, expected: $dbName-shm, received: $this"
            }
        }

        with(file.path(DatabaseFileType.Wal)) {
            assert(this.endsWith("$dbName-wal")) {
                "wal path not matching, expected: $dbName-wal, received: $this"
            }
        }
    }
}
