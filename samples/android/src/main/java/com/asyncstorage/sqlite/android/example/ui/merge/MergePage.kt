package com.asyncstorage.sqlite.android.example.ui.merge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asyncstorage.sqlite.android.example.ui.rememberStorage
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.launch
import org.asyncstorage.sqlitestorage.models.Entry
import kotlin.random.Random
import kotlin.random.nextInt

private val defaultKey = "default"

@Composable
fun MergePage(modifier: Modifier = Modifier) {
    val storage = rememberStorage(name = "merge-example.db")
    val scope = rememberCoroutineScope()
    var merged: String? by remember {
        mutableStateOf(null)
    }
    var mergeValue: String by remember {
        mutableStateOf(createMergingValue())
    }

    fun randomizeMergeValue() {
        mergeValue = createMergingValue()
    }


    fun reset() {
        merged = null
        scope.launch {
            storage.write(Entry(defaultKey, defaultValue))
        }
    }

    fun mergeValues() {
        scope.launch {
            val updated = storage.merge(Entry(key = defaultKey, value = mergeValue))
            merged = updated.value
        }
    }

    Column(
        modifier = modifier
            .verticalScroll(state = rememberScrollState())
            .padding(4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = ::randomizeMergeValue) {
                Text("randomize")
            }
            Button(onClick = ::mergeValues) {
                Text("merge")
            }
            Button(onClick = ::reset) {
                Text("Clear all", color = Color.Red)
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(defaultValue.prettyJson(), fontSize = 12.sp)
            Text(mergeValue.prettyJson(), fontSize = 12.sp)
        }
        if (merged != null) {
            Divider(modifier = Modifier.padding(vertical = 12.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = merged!!.prettyJson())
            }
        }
    }
}


private fun createMergingValue(): String {
    val index = { Random.nextInt(0..5) }
    val boolean = { Random.nextBoolean() }
    val tags = {
        val t = mutableListOf<String>()
        for (i in 0..<3) {
            t.add("tag-${Random.nextInt(0..6)}")
        }
        t
    }
    val randomInt = { Random.nextInt(10..20) }

    return """
{
  "index": ${index()},
  "isActive": ${boolean()},
  "age": 21,
  "address": "${Random.nextInt(4..9)}th street",
  "tags": ${tags()},
  "properties": {
    "topIndex": ${index()},
    "inner": ${boolean()},
    "innerList": [
      ${randomInt()},
      ${randomInt()},
      ${randomInt()}
    ],
    "innerProp": {
      "name": "overridden",
      "newProp": true
    }
  }
}
"""
}

private val defaultValue = """
{
   "index":0,
   "isActive":false,
   "age":29,
   "name":"Johanna Sykes",
   "tags":[
      "tag-0",
      "tag-1",
      "tag-2"
   ],
   "properties":{
      "topIndex":0,
      "inner":false,
      "innerList":[
         1,
         2,
         3
      ],
      "innerProp":{
         "keep":"me",
         "name":"original"
      }
   }
}
"""

private fun String.prettyJson(): String {
    val obj = JsonParser.parseString(this).asJsonObject
    val gson = GsonBuilder().setPrettyPrinting().create()
    return gson.toJson(obj)
}
