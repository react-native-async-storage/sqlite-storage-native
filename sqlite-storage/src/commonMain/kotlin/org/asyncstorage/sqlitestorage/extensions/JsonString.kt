package org.asyncstorage.sqlitestorage.extensions

import kotlinx.serialization.json.Json

/**
 * Checks if given string is valid JSON object or array
 */
fun String.isValidJson(): Boolean {
    return try {
        val element = Json.parseToJsonElement(this)
        val isArray = element.isArray()
        val isObject = element.isObject()
        isArray || isObject
    } catch (e: Throwable) {
        false
    }
}
