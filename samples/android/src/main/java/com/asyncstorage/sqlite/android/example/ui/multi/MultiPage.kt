package com.asyncstorage.sqlite.android.example.ui.multi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asyncstorage.sqlite.android.example.ui.rememberStorage
import kotlinx.coroutines.launch
import org.asyncstorage.sqlitestorage.models.Entry
import kotlin.math.min
import kotlin.random.Random
import kotlin.random.nextInt


@Composable
fun MultiPage(modifier: Modifier = Modifier) {
    val storage = rememberStorage(name = "multi-example.db")
    val scope = rememberCoroutineScope()
    val entries = remember {
        mutableStateListOf<Entry>()
    }

    DisposableEffect(key1 = storage) {
        val job = scope.launch {
            storage.readKeysAsFlow().collect { keys ->
                val updatedValues = storage.readMany(keys)
                entries.clear()
                entries.addAll(updatedValues)
            }
        }



        onDispose {
            job.cancel()
        }
    }

    fun addRandom() {
        val newEntries = mutableListOf<Entry>()
        for (i in 0..(Random.nextInt(6))) {
            val key = "key-${Random.nextInt(1..<15)}"
            val value = "value-${Random.nextInt(100..<1000)}"
            newEntries.add(Entry(key, value))
        }
        scope.launch {
            storage.writeMany(newEntries)
        }
    }

    fun removeRandom() {
        if (entries.size == 0) return
        val keys = mutableListOf<String>()
        for (i in 0..(min(1, (Random.nextInt(0..<entries.size))))) {
            val random = entries.random()
            keys.add(random.key)
        }

        scope.launch {
            storage.removeMany(keys)
        }
    }

    fun clearAll() {
        scope.launch {
            storage.clear()
        }
    }


    Column(modifier = modifier.padding(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = ::addRandom) {
                Text("Add random")
            }
            Button(onClick = ::removeRandom) {
                Text("Remove some")
            }
            Button(onClick = ::clearAll) {
                Text("Clear all")
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            items(
                items = entries.toList().sortedBy { it.key.drop(4).toInt() },
                key = { it.key + it.value }) { entry ->
                Row {
                    Text(text = "${entry.key} : ", fontSize = 18.sp)
                    Text(entry.value ?: "null", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }
}
