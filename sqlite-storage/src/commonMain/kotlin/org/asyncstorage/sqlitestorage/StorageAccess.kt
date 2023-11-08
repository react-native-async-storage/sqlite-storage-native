package org.asyncstorage.sqlitestorage

import kotlinx.coroutines.flow.Flow
import org.asyncstorage.sqlitestorage.models.Entry
import org.asyncstorage.sqlitestorage.models.Key

/**
 * Main interface to interact with SQLite database
 * The connection is required for any database access action, otherwise an exception is thrown.
 */
interface StorageAccess {
    /**
     * Reads a single entry, matching provided key.
     * If entry does not exist in database, Entry will contain `null` value.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun read(key: Key): Entry

    /**
     * Equivalent to [read] function, but returns a flow of Entry instead.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    fun readAsFlow(key: Key): Flow<Entry>

    /**
     * Writes a single entry to database.
     * If Entry already exists in db, it will be overridden with new value.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun write(entry: Entry)

    /**
     * Merges given entry's value with stored entry, based on Merger Algorithm.
     * Creates the entry if not existing.
     * Returns merged entry.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun merge(entry: Entry): Entry

    /**
     * Removes a single entry based on provided key.
     * If entry does not exist in db, the operation is a noop.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun remove(key: Key)

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
    fun readManyAsFlow(keys: List<Key>): Flow<List<Entry>>

    /**
     * Stores multiple entries in one transaction.
     * If an entry for matching key already exists, it will be overridden with new value.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun writeMany(entries: List<Entry>)

    /**
     * Merged multiple entries, with entries already existing.
     * Creates entries, if not currently saved.
     * Returns a list of merged entries.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun mergeMany(entries: List<Entry>): List<Entry>

    /**
     * Removes multiple entries that match Key in provided key list.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun removeMany(keys: List<Key>)

    /**
     * Returns a list of all Keys stored in db.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun getKeys(): List<Key>

    /**
     * Equivalent to [getKeys], but returns a Flow of List of keys instead.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    fun getKeysAsFlow(): Flow<List<Key>>

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

    /**
     * Returns an absolute path to database file on the device.
     */
    fun getDbPath(): String

    /**
     * Returns a size of database file on filesystem, in KB.
     * If file does not exist, or there's an exception while accessing it, returns -1.
     * Throws if DB connection is closed.
     */
    @Throws(Throwable::class)
    suspend fun getDbSize(): Long

    /**
     * Closes current connection to database and removes its file from filesystem.
     * After this action, this StorageAccess instance should no longer be used.
     * Returns boolean if file deletion was successful or not.
     * Throws if DB connection is closed.
     *
     * This action is not recoverable.
     */
    @Throws(Throwable::class)
    suspend fun dropDatabase(): Boolean
}
