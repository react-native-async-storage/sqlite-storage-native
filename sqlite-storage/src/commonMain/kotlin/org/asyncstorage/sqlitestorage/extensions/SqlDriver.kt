package org.asyncstorage.sqlitestorage.extensions

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver

/**
 * Calls checkpoint operation causing content from -wal file to be transferred to database file
 */
fun SqlDriver.executePragmaWalCheckpoint(): Boolean =
    executeQuery(0, "PRAGMA wal_checkpoint(FULL);", mapper = { c ->
        var success = false
        if (c.next().value) {
            /**
             * Result is usually 0 but will be 1 if (FULL) checkpoint was blocked from completing,
             * for example because another thread or process was actively using the database.
             */
            success = c.getLong(0) == 0L
        }
        QueryResult.Value(success)
    }, parameters = 0).value

/**
 * To achieve the best long-term query performance without the need to do a detailed engineering
 * analysis of the application schema and SQL, it is recommended that applications run "PRAGMA optimize" (with no arguments)
 * just before closing each database connection.
 *
 * https://www.sqlite.org/pragma.html#pragma_optimize
 */
fun SqlDriver.executePragmaOptimize() {
    executeQuery(null, sql = "PRAGMA optimize;", { QueryResult.Unit }, parameters = 0)
}
