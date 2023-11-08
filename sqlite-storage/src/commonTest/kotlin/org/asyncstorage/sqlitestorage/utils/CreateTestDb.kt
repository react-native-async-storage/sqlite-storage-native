package org.asyncstorage.sqlitestorage.utils

import kotlinx.coroutines.CoroutineDispatcher
import org.asyncstorage.sqlitestorage.StorageAccess

expect fun createTestDatabase(
    dbName: String,
    dispatcher: CoroutineDispatcher,
    inMemory: Boolean = false,
): StorageAccess
