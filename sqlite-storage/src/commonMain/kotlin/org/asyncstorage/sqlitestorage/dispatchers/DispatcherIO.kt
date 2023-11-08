package org.asyncstorage.sqlitestorage.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

expect val DispatcherIO: CoroutineDispatcher
