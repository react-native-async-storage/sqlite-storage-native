package com.asyncstorage.sqlite.android.example.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.asyncstorage.sqlitestorage.SQLiteStorage
import org.asyncstorage.sqlitestorage.SQLiteStorageFactory

@Composable
fun rememberStorage(name: String): SQLiteStorage {
    val ctx = LocalContext.current
    val storage = remember(name) {
        SQLiteStorageFactory(ctx).create(name)
    }

    DisposableEffect(key1 = storage) {
        onDispose {
            storage.closeConnection()
        }
    }

    return storage
}
