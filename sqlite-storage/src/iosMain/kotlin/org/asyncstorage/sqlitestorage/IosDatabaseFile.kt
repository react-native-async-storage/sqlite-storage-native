@file:OptIn(ExperimentalForeignApi::class)

package org.asyncstorage.sqlitestorage

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSBundle
import platform.Foundation.NSFileManager
import platform.Foundation.NSFileSize
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask
import platform.posix.remove

internal class IosDatabaseFile(private val dbName: String) : DatabaseFiles {
    /**
     * Main location to store database file is at
     * Application Support/App_Bundle_Id/${MainBundleName or SqliteStorage}/databases
     */
    @Suppress("UNCHECKED_CAST")
    private val dbDir by lazy {
        val paths =
            NSSearchPathForDirectoriesInDomains(
                NSApplicationSupportDirectory,
                NSUserDomainMask,
                true,
            ) as List<String>
        val bundleId = NSBundle.mainBundle.bundleIdentifier ?: "SQLiteStorage"
        val docDirectory = paths.first()
        "$docDirectory/$bundleId/databases"
    }

    override fun path(type: DatabaseFileType): String =
        when (type) {
            DatabaseFileType.Main -> "$dbDir/$dbName"
            DatabaseFileType.Wal -> "$dbDir/$dbName-wal"
            DatabaseFileType.Index -> "$dbDir/$dbName-shm"
        }

    override fun dirPath() = dbDir

    override fun delete(): Boolean {
        val deleted = mutableListOf<Boolean>()
        listOf(
            path(DatabaseFileType.Main),
            path(DatabaseFileType.Wal),
            path(DatabaseFileType.Index)
        ).forEach { file ->
            deleted += remove(file) == 0
        }
        return deleted.all { it }
    }

    override fun size(type: DatabaseFileType): Long? {
        val file = path(type)
        return try {
            val attrs = NSFileManager.defaultManager.attributesOfItemAtPath(file, null)
            val fileSize = attrs?.get(NSFileSize) as Long?
            fileSize?.let {
                it / 1024L
            }
        } catch (e: Exception) {
            return null
        }
    }

    /**
     * Make sure that database base parent directory exists upfront,
     * otherwise "Unable to open db" is thrown
     */
    internal fun createBaseDirectoryIfNotExisting() {
        val fm = NSFileManager.defaultManager
        if (!fm.fileExistsAtPath(dbDir)) {
            fm.createDirectoryAtPath(dbDir, true, null, null)
        }
    }
}
