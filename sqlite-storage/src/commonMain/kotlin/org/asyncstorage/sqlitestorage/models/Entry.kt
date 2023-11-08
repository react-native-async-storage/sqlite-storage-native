package org.asyncstorage.sqlitestorage.models

typealias Key = String
typealias Value = String?

data class Entry(val key: Key, val value: Value = null)
