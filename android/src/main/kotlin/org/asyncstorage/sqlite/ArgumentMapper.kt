package org.asyncstorage.sqlite

import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import org.asyncstorage.sqlitestorage.models.Entry
import org.asyncstorage.sqlitestorage.models.Key
import org.asyncstorage.sqlitestorage.models.Value


fun List<Entry>.toReadableMap(): ReadableMap {
    val map = WritableNativeMap()

    forEach {
        map.putString(it.key, it.value)
    }

    return map
}

fun Entry.toReadableMap(): ReadableMap = WritableNativeMap().apply {
    putString(key, value)
}

fun List<Key>.toReadableArray() = WritableNativeArray().apply {
    forEach {
        this.pushString(it)
    }
}

fun ReadableMap.toEntryList(): List<Entry> {
    val entries = mutableListOf<Entry>()

    for (entry in this.entryIterator) {
        entries.add(
            Entry(entry.key, entry.value as Value)
        )
    }
    return entries
}


fun ReadableArray.toKeyList() = toArrayList().toList() as List<Key>
