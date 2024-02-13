package org.asyncstorage.sqlitestorage

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.asyncstorage.sqlitestorage.extensions.isValidJson
import org.asyncstorage.sqlitestorage.utils.JunitRunner
import org.asyncstorage.sqlitestorage.utils.RunWith
import org.asyncstorage.sqlitestorage.utils.mergePossibleJsonValues
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(JunitRunner::class)
class MergeTest {
    @Test
    fun returns_new_value_if_one_or_both_values_are_null() {
        assertEquals("", mergePossibleJsonValues(old = null, new = ""))
        assertNull(mergePossibleJsonValues(old = "", new = null))
        assertNull(mergePossibleJsonValues(old = null, new = null))
    }

    @Test
    fun returns_new_value_if_one_of_them_is_not_json() {
        val validJson = """{"a":1,"b":true,"c":"hello","d":[1,2,3]}"""
        assertEquals("new_value", mergePossibleJsonValues(old = validJson, new = "new_value"))
        assertEquals(validJson, mergePossibleJsonValues(old = "old_value", new = validJson))
        assertEquals(validJson, mergePossibleJsonValues(old = null, new = validJson))
        assertEquals(null, mergePossibleJsonValues(old = validJson, new = null))
    }

    @Test
    fun is_string_valid_json() {
        assertTrue("{}".isValidJson())
        assertTrue("[]".isValidJson())
        assertTrue("""{"a":1,"b":true,"c":"hello","d":[1,2,3]}""".isValidJson())
        assertFalse("".isValidJson())
        assertFalse("nope".isValidJson())
        assertFalse("null".isValidJson())
        assertFalse("""{"a":1,"b":true,"c":"hello","d":[1,2,3""".isValidJson()) // syntax error
    }

    @Test
    fun merges_json_objects_deeply() {
        val old =
            """
            {
               "a": 100,
               "b": true,
               "c": [
                  "a",
                  "b"
               ],
               "d": {
                  "d1": false,
                  "d2": [
                     123,
                     456
                  ],
                  "d3": {
                     "e": true,
                     "f": null,
                     "g": [
                        true,
                        true
                     ]
                  }
               }
            }
            """.trimIndent()
        val new =
            """
            {
               "b": false,
               "b1": "new",
               "c": [
                  "c",
                  "d"
               ],
               "d": {
                  "d1": true,
                  "d2": {
                     "deep": true
                  },
                  "d3": {
                     "e": null,
                     "f": "new_value",
                     "g": [
                        false,
                        false
                     ]
                  }
               }
            }
            """.trimIndent()
        val merged =
            """
            {
               "b": false,
               "b1": "new",
               "c": [
                  "a",
                  "b",
                  "c",
                  "d"
               ],
               "d": {
                  "d1": true,
                  "d2": {
                    "deep": true
                  },
                  "d3": {
                     "e": null,
                     "f": "new_value",
                     "g": [
                        true,
                        true,
                        false,
                        false
                     ]
                  }
               },
               "a": 100
            }
            """.trimIndent()

        val result = mergePossibleJsonValues(old, new)
        assertEquals(merged.prettyJson(), result!!.prettyJson())
    }

    @Test
    fun merges_json_objects_deeply_2() {
        val original =
            """
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
                  "topIndex":1,
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
            """.trimIndent()
        val new =
            """
            {
               "index":4,
               "isActive":true,
               "age":21,
               "address":"6th street",
               "tags":[
                  "tag-4",
                  "tag-3",
                  "tag-0"
               ],
               "properties":{
                  "topIndex":0,
                  "inner":true,
                  "innerList":[
                     14,
                     13,
                     12
                  ],
                  "innerProp":{
                     "name":"overridden",
                     "newProp":true
                  }
               }
            }
            """.trimIndent()
        val merged =
            """
            {
              "index": 4,
              "isActive": true,
              "age": 21,
              "address": "6th street",
              "tags": ["tag-0", "tag-1", "tag-2", "tag-4", "tag-3", "tag-0"],
              "properties": {
                "topIndex": 0,
                "inner": true,
                "innerList": [
                  1,
                  2,
                  3,
                  14,
                  13,
                  12
                ],
                "innerProp": {
                  "name": "overridden",
                  "newProp": true,
                  "keep": "me"
                }
              },
              "name": "Johanna Sykes"
            }
            """.trimIndent()
        val result = mergePossibleJsonValues(original, new)
        assertEquals(merged.prettyJson(), result!!.prettyJson())
    }

    @Test
    fun merges_json_arrays() {
        val oldArray = """[123,456,789]"""
        val newArray = """["001", "002", "003"]"""
        val merged = mergePossibleJsonValues(oldArray, newArray)
        assertEquals("""[123,456,789,"001","002","003"]""", merged)
    }
}

private fun String.prettyJson(): String {
    val json =
        Json {
            prettyPrint = true
            prettyPrintIndent = " "
        }
    val el = json.parseToJsonElement(this)
    return json.encodeToString(el)
}
