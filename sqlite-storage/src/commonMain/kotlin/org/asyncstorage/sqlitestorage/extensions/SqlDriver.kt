package org.asyncstorage.sqlitestorage.extensions

import com.squareup.sqldelight.db.SqlDriver

/**
 * Makes sqlite use write-ahead logging for atomic commits and callback
 * Returns true if pragma ran successfully
 *
 * https://www.sqlite.org/wal.html
 */
fun SqlDriver.executePragmaWalJournalMode(): Boolean {
    val cursor = executeQuery(null, "PRAGMA journal_mode = WAL;", 0)
    if (!cursor.next()) {
        cursor.close()
        return false
    }
    val result = cursor.getString(0)
    cursor.close()
    return result == "wal"
}

/**
 * Makes sqlite use Normal mode for synchronizing DB content with filesystem only in crucial times
 * WAL file is synchronized before each checkpoint and the database file is synchronized after each completed checkpoint
 *
 * https://sqlite.org/pragma.html#pragma_synchronous
 */
fun SqlDriver.executePragmaSyncNormal() {
    executeQuery(null, "PRAGMA synchronous = NORMAL;", 0).close()
}

/**
 * Calls checkpoint operation causing content from -wal file to be transferred to database file
 */
fun SqlDriver.executePragmaWalCheckpoint(): Boolean {
    val cursor = executeQuery(null, "PRAGMA wal_checkpoint(FULL);", 0)
    if (!cursor.next()) {
        cursor.close()
        return false
    }

    /**
     * This column is usually 0 but will be 1 if (FULL) checkpoint was blocked from completing,
     * for example because another thread or process was actively using the database.
     */
    val result = cursor.getLong(0)
    cursor.close()
    return result == 0L
}

/**
 * To achieve the best long-term query performance without the need to do a detailed engineering
 * analysis of the application schema and SQL, it is recommended that applications run "PRAGMA optimize" (with no arguments)
 * just before closing each database connection.
 *
 * https://www.sqlite.org/pragma.html#pragma_optimize
 */
fun SqlDriver.executePragmaOptimize() {
    executeQuery(null, "PRAGMA optimize;", 0).close()
}
