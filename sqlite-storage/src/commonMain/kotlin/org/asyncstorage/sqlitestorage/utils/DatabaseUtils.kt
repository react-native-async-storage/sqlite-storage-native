package org.asyncstorage.sqlitestorage.utils

/**
 * A helper class that manages the database file
 */
internal expect class DatabaseUtils {
    /**
     * Returns an absolute path to database file
     */
    fun getDbFilePath(): String

    /**
     * Removes the database file and all related WAL journal files.
     */
    fun removeDbFiles(): Boolean

    /**
     * Returns database file size in KB.
     * If file does not exists or cannot be reached due to security returns 0.
     * This only checks the size of main database file, without log file (-wal) or wal-index file (-shm)
     * To get the correct values, call this after manual WAL checkpoint.
     */
    fun getDbFileSize(): Long
}
