package org.asyncstorage.sqlitestorage

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
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
        val merged =
            Json.parseToJsonElement(
                mergePossibleJsonValues(
                    OldJsonValue,
                    NewJsonValue,
                )!!,
            ).jsonObject
        // primitive json values
        listOf(
            "a" to "100",
            "b" to "false",
            "b1" to "new",
        ).forEach { (key: String, value: String) ->
            assertTrue(merged.containsKey(key))
            assertEquals(value, merged[key]!!.jsonPrimitive.content)
        }

        assertTrue(merged.containsKey("c"))
        assertEquals("""["a","b","c","d"]""", merged["c"]!!.jsonArray.toString())

        assertTrue(merged.containsKey("d"))
        // nested "d" object
        val dContent = merged["d"]!!.jsonObject
        assertEquals("true", dContent["d1"]!!.jsonPrimitive.content)
        assertEquals("""{"deep":true}""", dContent["d2"]!!.jsonObject.toString())
        // nested "d3" object
        val d3Content = dContent["d3"]!!.jsonObject
        assertEquals("true", d3Content["e"]!!.jsonPrimitive.content)
        assertEquals("new_value", d3Content["f"]!!.jsonPrimitive.content)
        assertEquals("""[true,true,false,false]""", d3Content["g"]!!.jsonArray.toString())
    }

    @Test
    fun merges_json_arrays() {
        val oldArray = """[123,456,789]"""
        val newArray = """["001", "002", "003"]"""
        val merged = mergePossibleJsonValues(oldArray, newArray)
        assertEquals("""[123,456,789,"001","002","003"]""", merged)
    }
}

private val OldJsonValue =
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
private val NewJsonValue =
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
             "f": "new_value",
             "g": [
                false,
                false
             ]
          }
       }
    }
    """.trimIndent()
