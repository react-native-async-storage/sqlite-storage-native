package org.asyncstorage.sqlitestorage

expect class SqliteStorageFactory {
    fun create(dbName: String): SqliteStorage
}
