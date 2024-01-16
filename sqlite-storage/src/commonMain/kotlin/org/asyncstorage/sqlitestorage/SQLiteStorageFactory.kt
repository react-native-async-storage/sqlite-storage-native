package org.asyncstorage.sqlitestorage

import kotlin.native.ObjCName

expect class SQLiteStorageFactory {
    fun create(dbName: String): SQLiteStorage
}
