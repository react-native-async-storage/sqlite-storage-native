package org.asyncstorage.sqlitestorage.models

import kotlin.native.ObjCName

typealias Key = String
typealias Value = String?

@ObjCName(name = "EntryKMP", swiftName = "EntryKMP")
data class Entry(val key: Key, val value: Value = null)
