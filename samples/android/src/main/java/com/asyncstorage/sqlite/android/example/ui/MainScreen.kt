package com.asyncstorage.sqlite.android.example.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.asyncstorage.sqlite.android.example.ui.components.SingleValue
import org.asyncstorage.sqlitestorage.SQLiteStorage


@Composable
fun MainScreen(db: SQLiteStorage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp, horizontal = 8.dp)
            .scrollable(orientation = Orientation.Vertical, state = rememberScrollState())
    ) {
        SingleValue(db)
    }
}
