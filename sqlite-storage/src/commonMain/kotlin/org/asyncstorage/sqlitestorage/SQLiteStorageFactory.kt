package org.asyncstorage.sqlitestorage

expect class SQLiteStorageFactory {
    fun create(dbName: String): SQLiteStorage
}
