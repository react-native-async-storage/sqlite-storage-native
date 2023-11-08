package org.asyncstorage.sqlitestorage.extensions

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun JsonElement.isArray(): Boolean =
    try {
        jsonArray
        true
    } catch (e: Throwable) {
        false
    }

fun JsonElement.isObject(): Boolean =
    try {
        jsonObject
        true
    } catch (e: Throwable) {
        false
    }
