package org.asyncstorage.sqlitestorage

import java.io.File

internal class AndroidDatabaseFile(private val dbFile: File) : DatabaseFile {
    override fun path(): String = dbFile.absolutePath

    override fun dirPath(): String = dbFile.parentFile!!.absolutePath

    override fun delete(): Boolean {
        val dbName = dbFile.name
        return try {
            val parent = dbFile.parentFile!!
            val deleted = mutableListOf<Boolean>()

            listOf(dbName, "$dbName-wal", "$dbName-shm").forEach { dbFile ->
                deleted += File(parent, dbFile).delete()
            }
            true
        } catch (e: SecurityException) {
            e.printStackTrace()
            return false
        }
    }

    override fun size(): Long {
        var size = -1L
        if (dbFile.exists()) {
            size = (dbFile.length() / 1024L)
        }
        return size
    }
}
