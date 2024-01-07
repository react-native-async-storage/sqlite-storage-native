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

internal class IosDatabaseFile(private val dbName: String) : DatabaseFile {
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
        val bundleId = NSBundle.mainBundle.bundleIdentifier ?: "SqliteStorage"
        val docDirectory = paths.first()
        "$docDirectory/$bundleId/databases"
    }

    override fun path(): String = "$dbDir/$dbName"

    override fun dirPath() = dbDir

    override fun delete(): Boolean {
        val deleted = mutableListOf<Boolean>()
        val dbFile = path()
        listOf(dbFile, "$dbFile-wal", "$dbFile-shm").forEach { file ->
            deleted += remove(file) == 0
        }
        return deleted.all { it }
    }

    override fun size(): Long {
        val file = path()
        var size = -1L
        try {
            val attrs = NSFileManager.defaultManager.attributesOfItemAtPath(file, null)
            val fileSize = attrs?.get(NSFileSize) as Long?
            if (fileSize != null) {
                size = fileSize / 1024L
            }
        } catch (e: Exception) {
            // potentially means trying to get attributes file that does not exist
        }
        return size
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
