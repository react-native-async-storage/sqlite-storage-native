package com.asyncstorage.sqlite.android.example.ui.keys

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asyncstorage.sqlite.android.example.ui.rememberStorage
import kotlinx.coroutines.launch
import org.asyncstorage.sqlitestorage.models.Entry
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun KeysPage(modifier: Modifier = Modifier) {
    val storage = rememberStorage(name = "keys-example.db")
    val scope = rememberCoroutineScope()
    val keys = remember { mutableStateListOf<String>() }

    DisposableEffect(key1 = storage) {
        val job = scope.launch {
            storage.readKeysAsFlow().collect { k ->
                val readValues = storage.readMany(k)
                keys.clear()
                keys.addAll(readValues.map { it.key })
            }
        }



        onDispose {
            job.cancel()
        }
    }

    fun addRandom() {
        val key = "key-${Random.nextInt(1..10000)}"
        scope.launch {
            storage.write(Entry(key, "value-$key"))
        }
    }


    fun removeEntry(key: String) {
        scope.launch {
            storage.remove(key)
        }
    }

    Column(modifier = modifier.padding(4.dp)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(onClick = ::addRandom) {
                Text("Add Entry")
            }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items = keys.toList(), { it }) { key ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { removeEntry(key) }, modifier = Modifier
                        .padding(4.dp)
                        .size(20.dp)
                    ) {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = null,
                            tint = Color.Red,
                        )
                    }
                    Text(key, fontSize = 18.sp)
                }
            }
        }
    }
}
