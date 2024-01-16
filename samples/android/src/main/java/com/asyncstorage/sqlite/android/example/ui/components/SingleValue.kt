package com.asyncstorage.sqlite.android.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.asyncstorage.sqlitestorage.SQLiteStorage
import org.asyncstorage.sqlitestorage.models.Entry

@Composable
fun SingleValue(db: SQLiteStorage) {
    val key = "my_single_value_key"
    var input by remember { mutableStateOf("") }
    var valueRead by remember { mutableStateOf<String?>(null) }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    fun readValueFromStorage() {
        scope.launch {
            val entry = db.read(key)
            valueRead = entry.value
        }
    }

    fun saveInputToStorage() {
        scope.launch {
            val entry = Entry(key, input.ifEmpty { null })
            db.write(entry)
        }
    }

    fun removeValueFromStorage() {
        scope.launch {
            db.remove(key)
        }
    }

    Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
        Text("Single value: $valueRead", style = TextStyle(fontSize = 14.sp))

        OutlinedTextField(
            value = input,
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            onValueChange = { input = it })

        Row(
            modifier = Modifier.fillMaxSize().padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { readValueFromStorage() }) {
                Text("Read Value")
            }
            OutlinedButton(onClick = { saveInputToStorage() }) {
                Text("Save Value")
            }
            Button(
                onClick = { removeValueFromStorage() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text("Delete value", color = Color.White)
            }
        }
    }
}
