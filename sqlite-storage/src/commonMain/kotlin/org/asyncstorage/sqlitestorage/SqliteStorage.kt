package org.asyncstorage.sqlitestorage

import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.flow.Flow
import org.asyncstorage.sqlitestorage.models.Entry
import org.asyncstorage.sqlitestorage.models.Key

/**
 * Main interface to interact with Sqlite database
 * The connection is required for any database access action, otherwise an exception is thrown.
 */
interface SqliteStorage {
    /**
     * Access database files related to instance of this SqliteStorage directly
     * See [DatabaseFiles]
     */
    val files: DatabaseFiles

    /**
     * Reads a single entry, matching provided key.
     * If entry does not exist in database, Entry will contain `null` value.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun read(key: Key): Entry

    /**
     * Returns a list of entries that match given key list.
     * If an entry is not found for selected key, the returned Entry will have `null` value.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun readMany(keys: List<Key>): List<Entry>

    /**
     * Equivalent to [readMany] function, but returns a Flow of List of entries instead.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    fun readAsFlow(keys: List<Key>): Flow<List<Entry>>

    /**
     * Writes a single entry to database.
     * If Entry already exists in db, it will be overridden with new value.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun write(entry: Entry)

    /**
     * Stores multiple entries in one transaction.
     * If an entry for matching key already exists, it will be overridden with new value.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun writeMany(entries: List<Entry>)

    /**
     * Removes a single entry based on provided key.
     * If entry does not exist in db, the operation is a noop.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun remove(key: Key)

    /**
     * Removes multiple entries that match Key in provided key list.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun removeMany(keys: List<Key>)

    /**
     * Merges given entry's value with stored entry, based on Merger Algorithm.
     * Creates the entry if not existing.
     * Returns merged entry.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun merge(entry: Entry): Entry

    /**
     * Merged multiple entries, with entries already existing.
     * Creates entries, if not currently saved.
     * Returns a list of merged entries.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun mergeMany(entries: List<Entry>): List<Entry>

    /**
     * Returns a list of all Keys stored in db.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun readKeys(): List<Key>

    /**
     * Equivalent to [readKeys], but returns a Flow of List of keys instead.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    fun readKeysAsFlow(): Flow<List<Key>>

    /**
     * Clears every Entry from the database, effectively bringing it to empty state.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun clear()

    /**
     * Closes the connection to database.
     * Once it's done, the StorageAccess instance should not be used anymore for database interactions.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun closeConnection()
}

fun SqliteStorage(
    driver: SqlDriver,
    dbFile: DatabaseFiles,
): SqliteStorage {
    return DefaultSqliteStorage(
        driver = driver,
        files = dbFile,
    )
}
