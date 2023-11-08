package org.asyncstorage.sqlitestorage.utils

import org.asyncstorage.sqlitestorage.models.AppContext

internal actual class DatabaseUtils(private val dbName: String, private val ctx: AppContext) {
    actual fun getDbFilePath(): String = ctx.getDatabasePath(dbName).absolutePath

    actual fun getDbFileSize(): Long {
        var size = -1L
        try {
            val file = ctx.getDatabasePath(dbName)
            if (file.exists()) {
                size = (file.length() / 1024)
            }
        } catch (e: SecurityException) {
        }
        return size
    }

    actual fun removeDbFiles(): Boolean {
        return try {
            val deleted = mutableListOf<Boolean>()
            listOf(dbName, "$dbName-wal", "$dbName-shm").forEach { dbFile ->
                deleted += ctx.getDatabasePath(dbFile).delete()
            }
            deleted.all { it }
        } catch (e: SecurityException) {
            return false
        }
    }
}
