package org.asyncstorage.sqlitestorage

import java.io.File

internal class AndroidDatabaseFile(private val dbFile: File) : DatabaseFiles {
    override fun path(type: DatabaseFileType): String =
        when (type) {
            DatabaseFileType.Main -> dbFile.absolutePath
            DatabaseFileType.Wal -> "${dbFile.absolutePath}-wal"
            DatabaseFileType.Index -> "${dbFile.absolutePath}-shm"
        }

    override fun dirPath(): String = dbFile.parentFile!!.absolutePath

    override fun delete(): Boolean {
        return try {
            val deleted = mutableListOf<Boolean>()

            listOf(
                path(DatabaseFileType.Main),
                path(DatabaseFileType.Wal),
                path(DatabaseFileType.Index),
            ).forEach { path ->
                deleted += File(path).delete()
            }
            true
        } catch (e: SecurityException) {
            e.printStackTrace()
            return false
        }
    }

    override fun size(type: DatabaseFileType): Long? {
        val file = File(path(type))
        return if (file.exists()) {
            (dbFile.length() / 1024L)
        } else {
            null
        }
    }
}
