@file:OptIn(ExperimentalForeignApi::class)

package org.asyncstorage.sqlitestorage.utils

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSBundle
import platform.Foundation.NSFileManager
import platform.Foundation.NSFileSize
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask
import platform.posix.remove

internal actual class DatabaseUtils(private val dbName: String) {
    /**
     * Database is located at
     *  Application Support/App_Bundle_Id/${MainBundlename or SqliteStorage}/databases
     */
    fun getDbBasePath(): String {
        val paths =
            NSSearchPathForDirectoriesInDomains(
                NSApplicationSupportDirectory,
                NSUserDomainMask,
                true,
            )
        val bundleId = NSBundle.mainBundle.bundleIdentifier ?: "SqliteStorage"
        val docDirectory = paths[0] as String
        return "$docDirectory/$bundleId/databases"
    }

    /**
     * Make sure that database base parent directory exists upfront,
     * otherwise "Unable to open db" is thrown
     */
    fun createBaseDirectoryIfNotExisting() {
        val path = getDbBasePath()
        val fm = NSFileManager.defaultManager
        if (!fm.fileExistsAtPath(path)) {
            fm.createDirectoryAtPath(path, true, null, null)
        }
    }

    actual fun getDbFilePath(): String = "${getDbBasePath()}/$dbName"

    actual fun removeDbFiles(): Boolean {
        val deleted = mutableListOf<Boolean>()
        val dbFile = getDbFilePath()
        listOf(dbFile, "$dbFile-wal", "$dbFile-shm").forEach { file ->
            deleted += remove(file) == 0
        }
        return deleted.all { it }
    }

    actual fun getDbFileSize(): Long {
        val file = getDbFilePath()
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
}
