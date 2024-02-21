package org.asyncstorage.sqlitestorage

// todo: bring implementation to common, rather per platform
//       requires per platform file access
interface DatabaseFiles {
    /**
     * Returns an absolute path to the selected database file
     */
    fun path(type: DatabaseFileType = DatabaseFileType.Main): String

    /**
     * An absolute path to a directory containing database files
     */
    fun dirPath(): String

    /**
     * Removes the database file and all related WAL files (-wal and -shm).
     */
    fun delete(): Boolean

    /**
     * Returns a size of a selected database file in bytes
     * If the file does not exists or cannot be read for some reason, returns null
     */
    fun size(type: DatabaseFileType = DatabaseFileType.Main): Long?
}

/**
 * https://www.sqlite.org/walformat.html
 */
enum class DatabaseFileType {
    Main,
    Wal,
    Index,
}
