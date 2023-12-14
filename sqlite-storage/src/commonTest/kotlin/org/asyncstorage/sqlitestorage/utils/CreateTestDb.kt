package org.asyncstorage.sqlitestorage.utils

import kotlinx.coroutines.CoroutineDispatcher
import org.asyncstorage.sqlitestorage.SqliteStorage

expect fun createTestDatabase(
    dbName: String,
    dispatcher: CoroutineDispatcher,
): SqliteStorage
