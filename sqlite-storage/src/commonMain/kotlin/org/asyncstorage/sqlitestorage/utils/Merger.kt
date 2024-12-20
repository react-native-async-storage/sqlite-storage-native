package org.asyncstorage.sqlitestorage.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.asyncstorage.sqlitestorage.extensions.isArray
import org.asyncstorage.sqlitestorage.extensions.isObject
import org.asyncstorage.sqlitestorage.extensions.isValidJson

/**
 * Merges two serialized json values.
 *
 * Merging rules:
 * - if either value is null, return new value
 * - if either value is not Json object or Json array, return new value
 * - if both values are valid JSON array, append new one to old one
 * - if both values are valid JSON object, map over combined array of keys from both objects:
 *   - if key is unique to only one object, keep its key/value
 *   - if key is present in both objects, it's value:
 *      - if primitive or null value, keep new's key/value
 *      - for arrays, append new one to old one
 *      - for objects, repeat JSON object merge recursively
 */
internal fun mergePossibleJsonValues(
    old: String?,
    new: String?,
): String? {
    if (old == null || new == null) {
        return new
    }

    if (!old.isValidJson() || !new.isValidJson()) {
        return new
    }
    val oldJson = Json.parseToJsonElement(old)
    val newJson = Json.parseToJsonElement(new)
    val result = mergeElements(oldJson, newJson)
    return Json.encodeToString(result)
}

private fun mergeElements(
    old: JsonElement,
    new: JsonElement,
): JsonElement =
    if (old.isArray() && new.isArray()) {
        mergeArrays(old.jsonArray, new.jsonArray)
    } else if (old.isObject() && new.isObject()) {
        mergeObjects(old.jsonObject, new.jsonObject)
    } else {
        new
    }

private fun mergeArrays(
    old: JsonArray,
    new: JsonArray,
): JsonArray {
    val merged = old.toMutableList()
    for (element in new) {
        merged.add(element)
    }
    return JsonArray(merged.toList())
}

private fun mergeObjects(
    old: JsonObject,
    new: JsonObject,
): JsonObject {
    val merged = mutableMapOf<String, JsonElement>()
    val allKeys = new.keys + old.keys

    for (key in allKeys) {
        if (!old.containsKey(key)) {
            merged[key] = new[key] ?: JsonNull
        } else if (!new.containsKey(key)) {
            merged[key] = old[key] ?: JsonNull
        } else {
            val oldKeyContent = old[key] ?: JsonNull
            val newKeyContent = new[key] ?: JsonNull
            merged[key] = mergeElements(oldKeyContent, newKeyContent)
        }
    }

    return JsonObject(merged.toMap())
}
