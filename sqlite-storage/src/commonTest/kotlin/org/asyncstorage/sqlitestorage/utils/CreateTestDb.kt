package org.asyncstorage.sqlitestorage.utils

import kotlinx.coroutines.CoroutineDispatcher
import org.asyncstorage.sqlitestorage.SQLiteStorage

expect fun createTestDatabase(
    dbName: String,
    dispatcher: CoroutineDispatcher,
): SQLiteStorage
