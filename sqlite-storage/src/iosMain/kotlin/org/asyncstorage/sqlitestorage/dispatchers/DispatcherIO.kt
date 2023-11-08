package org.asyncstorage.sqlitestorage.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val DispatcherIO: CoroutineDispatcher
    get() = Dispatchers.Default
