package org.asyncstorage.sqlitestorage

interface DatabaseFile {
    /**
     * Returns an absolute path to main .db file
     */
    fun path(): String

    /**
     * An absolute path to a directory containing database files
     */
    fun dirPath(): String

    /**
     * Removes the database file and all related WAL files (-wal and -shm).
     */
    fun delete(): Boolean

    /**
     * Returns database file size in KB.
     * If file does not exists or cannot be reached due to security returns 0.
     * This only checks the size of main database file, without log file (-wal) or wal-index file (-shm)
     * To get the correct values, call this after manual WAL checkpoint.
     */
    fun size(): Long
}
