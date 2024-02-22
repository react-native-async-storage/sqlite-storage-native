package com.asyncstorage.sqlite.android.example.ui.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asyncstorage.sqlite.android.example.ui.rememberStorage
import kotlinx.coroutines.launch
import org.asyncstorage.sqlitestorage.models.Entry
import kotlin.random.Random


@Composable
fun BasicPage(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(vertical = 8.dp)) {
        var databaseName by remember {
            mutableStateOf("basic.db")
        }

        DatabaseNamePicker(databaseName) { databaseName = it }
        DatabaseActions(databaseName)
    }
}

@Composable
fun DatabaseNamePicker(initial: String, onPick: (String) -> Unit) {
    var databaseName by remember {
        mutableStateOf(initial)
    }
    val focusManager = LocalFocusManager.current


    fun submitName() {
        onPick(databaseName)
        focusManager.clearFocus()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TextField(
            value = databaseName,
            onValueChange = { databaseName = it },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { submitName() })
        )
        Button(onClick = { submitName() }) {
            Text("Select")
        }
    }
}


const val KEY = "random"

@Composable
fun DatabaseActions(dbName: String) {
    val database = rememberStorage(name = dbName)
    var currentValue: String? by remember(database) {
        mutableStateOf(null)
    }
    val changesInTime = remember(database) {
        mutableStateListOf<String?>()
    }
    val scope = rememberCoroutineScope()

    DisposableEffect(key1 = database) {
        val job = scope.launch {
            database.readAsFlow(listOf(KEY)).collect {
                changesInTime.add(it.first().value)
            }
        }

        onDispose {
            job.cancel()
        }
    }


    fun saveRandom() {
        scope.launch {
            val value = Random.nextInt(from = 1, until = 100).toString()
            val entry = Entry(KEY, value)
            database.write(entry)
        }
    }

    fun readRandom() {
        scope.launch {
            val result = database.read(KEY)
            currentValue = result.value ?: "null"
        }
    }

    fun removeRandom() {
        scope.launch {
            database.remove(KEY)
        }
    }



    Column {
        Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("database")
            Spacer(modifier = Modifier.size(4.dp))
            Text(dbName, fontWeight = FontWeight.Bold)
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { saveRandom() }) {
                Text("Save")
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(onClick = { readRandom() }) {
                Text("Read")
            }
            Spacer(modifier = Modifier.width(4.dp))
            Button(
                onClick = { removeRandom() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text("Remove", color = Color.White)
            }
        }

        Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("current value:")
            Spacer(modifier = Modifier.size(4.dp))
            Text(currentValue ?: "null", fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))
        Divider()
        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { changesInTime.clear() }, modifier = Modifier
                .padding(4.dp)
                .size(20.dp)
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = null,
                    tint = Color.Red,
                )
            }
            Text("Value changes in time:", fontSize = 14.sp)
        }
        Column(
            modifier = Modifier
              .fillMaxWidth()
              .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (change in changesInTime) {
                Text(
                    text = "updated to: ${change ?: "null"}",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(8.dp),
                )
                Divider()
            }
        }
    }
}
