## SQLiteStorage

### Creating an instance

=== "kotlin"

    ```{.kotlin .title="database.kt" .copy}
    import org.asyncstorage.sqlitestorage.SQLiteStorageFactory

    val storage = SQLiteStorageFactory(this).create("my_database_name")
    ```

=== "swift"

    ```{.swift .title="database.swift" .copy}
    import AsyncStorageSQLite

    // The iOS implementation provides a wrapper for SQLiteStorage, 
    // abstracting away the need for a factory method call.
    let storage = AsyncStorageSQLite("my_database_name")
    ```

### Reading

To read a single entry from the database, use the `read` method.

=== "kotlin"

    ```{.kotlin .title="read-single.kt" .copy}
    suspend fun single(): Entry {
        val entry = storage.read("my_key")
        return entry
    }
    ```

=== "swift"

    ```{.swift .title="read-single.swift" .copy}
    func single() async throws -> Entry {
        let entry = try await storage.read(key: "my_key")
        return entry
    }
    ```

To read multiple entries from the database, use the `readMany` method.

=== "kotlin"

    ```{.kotlin .title="read-many.kt" .copy}
    suspend fun multi(): List<Entry> {
        val keys = listOf("key1", "key2")
        val entry = storage.readMany(keys)
        return entry
    }
    ```

=== "swift"

    ```{.swift .title="read-many.swift" .copy}
    func multi() async throws -> [Entry] {
        let keys: [String] = ["key1", "key2"]
        let entries = try await storage.readMany(keys: keys)
        return entries
    }
    ```

You can also read entries as
a [`Flow`](https://kotlinlang.org/docs/flow.html)/[`AsyncSequence`](https://developer.apple.com/documentation/swift/asyncsequence)
using the `readAsFlow` method. A change in any of provided entries will emit an updated list of
entries.

=== "kotlin"

    ```{.kotlin .title="read-single-flow.kt" .copy}
    suspend fun singleAsFlow() {
        val keys = listOf("key1", "key2", "key3")
        storage.readAsFlow(keys).collect { entries -> 
            updateUi(entries)
        }
    }
    ```

=== "swift"

    ```{.swift .title="read-single-flow.swift" .copy}
    func singleAsFlow() async throws {
        let keys: [String] = ["key1", "key2", "key3"]
        for try await entries in storage.readAsFlow(keys: keys) {
            updateUi(entries: entries)
        }
    }
    ```

### Writing

To write a single entry to the database, use the `write` method.

=== "kotlin"

    ```{.kotlin .title="write-single.kt" .copy}
    suspend fun singleWrite() {
        val entry = Entry("my_key", "my_value")
        storage.write(entry)
    }
    ```

=== "swift"

    ```{.swift .title="write-single.swift" .copy}
    func singleWrite() async throws {
        let entry = Entry(key: "my_key", value: "my_value")
        try await storage.write(entry: entry)
    }
    ```

To write multiple entries to the database, use the writeMany method.

=== "kotlin"

    ```{.kotlin .title="write-single.kt" .copy}
    suspend fun manyWrite() {
        val entry1 = Entry("key1", """[1, 2, 3]""")
        val entry2 = Entry("key2", "true")
        val entry3 = Entry("key3", null)
        storage.writeMany(listOf(entry1, entry2, entry3))
    }
    ```

=== "swift"

    ```{.swift .title="write-single.swift" .copy}
    func manyWrite() async throws {
        let entry1 = Entry(key: "key1", value: "[1, 2, 3]")
        let entry2 = Entry(key: "key2", value: "true")
        let entry3 = Entry(key: "key3", value: nil)
        try await storage.writeMany(entries: [entry1, entry2, entry3])
    }
    ```

### Removing

To remove a single entry from the database, use the `remove` method.

=== "kotlin"

    ```{.kotlin .title="remove-single.kt" .copy}
    suspend fun removeSingle() {
        storage.remove("my_key")
    }
    ```

=== "swift"

    ```{.swift .title="remove-single.swift" .copy}
    func removeSingle() async throws {
        try await storage.remove(key: "my_key")
    }
    ```

To remove multiple entries from the database, use the `removeMany` method.

=== "kotlin"

    ```{.kotlin .title="remove-many.kt" .copy}
    suspend fun removeMany() {
        val keys = listOf("my_key1", "my_key2")
        storage.removeMany(keys)
    }
    ```

=== "swift"

    ```{.swift .title="remove-many.swift" .copy}
    func removeMany() async throws {
        let keys: [String] = ["my_key1", "my_key2"]
        try await storage.removeMany(keys: keys)
    }
    ```

### Merging

!!! info

    Merging rules:

    - if either value is null, return new value
    - if either value is not Json object or Json array, return new value
    - if both values are valid JSON array, append new one to old one
    - if both values are valid JSON object, merge them deeply following **merging rules**

To merge a single entry in the database, use the `merge` method.

=== "kotlin"

    ```{.kotlin .title="merge-single.kt" .copy}
    suspend fun singleMerge() {
        val entry = Entry("key", """{"name":"jerry","friends":[]}""")
        storage.write(entry)
        val updated = Entry("key", """{"friends": ["thomas"], "age": 30}""")
        val result = storage.merge(updated) 
        // result: {"name": "jerry", friends": ["thomas"], "age": 30}
    } 
    ```

=== "swift"

    ```{.swift .title="merge-single.swift" .copy}
    func singleMerge() async throws {
        let entry = Entry(key: "key", value: #"{"name":"jerry","friends":[]}"#)
        try await storage.write(entry: entry)
        let updated = Entry(key: "key", value: #"{"friends": ["thomas"], "age": 30}"#)
        let result = try await storage.merge(entry: updated)
        // result: {"name": "jerry", friends": ["thomas"], "age": 30}
    }
    ```

To merge multiple entries in the database, use the `mergeMany` method.

=== "kotlin"

    ```{.kotlin .title="merge-many.kt" .copy}
    suspend fun manyMerge() {
        val entry1 = Entry("key", """{"name":"henry","friends":[]}""")
        val entry2 = Entry("key", """{"friends":["julia"]}""")
        val entry3 = Entry("key", """{"age": 40}""")
        storage.write(entry1)
        val result = storage.mergeMany(listOf(entry2, entry3))
        // result: {"name": "henry", friends": ["julia"], "age": 40}
    }
    ```

=== "swift"

    ```{.swift .title="merge-many.swift" .copy}
    func manyMerge() async throws {
        let entry1 = Entry(key: "key", value: #"{"name":"henry","friends":[]}"#)
        let entry2 = Entry(key: "key", value: #"{"friends":["julia"]}"#)
        let entry3 = Entry(key: "key", value: #"{"age": 40}"#)
        try await storage.write(entry: entry1)
        let result = try await storage.mergeMany(entries: [entry2, entry3])
        // result: {"name": "henry", friends": ["julia"], "age": 40}
    }
    ```

### Reading keys

To retrieve all keys used to store entries in the database, use the `readKeys` method.

=== "kotlin"

    ```{.kotlin .title="keys-single.kt" .copy}
    suspend fun keysSingle(): List<String> {
        val keys = storage.readKeys()
        return keys
    }
    ```

=== "swift"

    ```{.swift .title="keys-single.swift" .copy}
    func keysSingle() async throws -> [String] {
        let keys = try await storage.readKeys()
        return keys
    }
    ```

You can also read keys as
a [`Flow`](https://kotlinlang.org/docs/flow.html)/[`AsyncSequence`](https://developer.apple.com/documentation/swift/asyncsequence)
using the `readKeysAsFlow` method. Any change in the entries within the provided key list will
trigger an emit of the key list again.

=== "kotlin"

    ```{.kotlin .title="keys-many.kt" .copy}
    suspend fun keysMany() {
        storage.readKeysAsFlow().collect { keys ->
            println("keys used: $keys")
        }
    }
    ```

=== "swift"

    ```{.swift .title="keys-many.swift" .copy}
    func keysMany() async throws {
        for try await keys in storage.readKeysAsFlow() {
            print("keys used: \(keys)")
        }
    }
    ```

### Clearing

To clear all entries from the database, use the `clear` method.

!!! warning

    This action wipes out whole database.

=== "kotlin"

    ```{.kotlin .title="clearing.kt" .copy}
    suspend fun clearStorage() {
        storage.clear()
    }
    ```

=== "swift"

    ```{.swift .title="clearing.swift" .copy}
    func clearStorage() async throws {
        try await storage.clear()
    }
    ```

### Close connection

To close the connection to the storage, use `closeConnection()` method. Before closing,
the [`PRAGMA optimize;`](https://www.sqlite.org/pragma.html#pragma_optimize) is executed.

!!! warning

    Once connection is closed, the storage instance is no longer usable for database reads/writes and will throw an error when done so

=== "kotlin"

    ```{.kotlin .copy}
    storage.closeConnection()
    ```

=== "swift"

    ```{.swift .copy}
    storage.closeConnection()
    ```

## DatabaseFiles

The `DatabaseFiles` interface provides methods to access information about database files for
given `SQLiteStorage` instance.

### Path to a storage file

Returns an absolute path to the selected database file. Default type is `DatabaseFileType.Main`.

=== "kotlin"

    ```{.kotlin .copy}
    val path = storage.files.path(DatabaseFileType.Index)
    ```

=== "swift"

    ```{.swift .copy}
    let path = storage.files.path(type: .index)
    ```

### Path to containing directory

Returns an absolute path to a directory containing database files.

=== "kotlin"

    ```{.kotlin .copy}
    val path = storage.files.dirPath()
    ```

=== "swift"

    ```{.swift .copy}
    let path = storage.files.dirPath()
    ```

### Delete database file

Removes the database file and all related Write-Ahead Logging (WAL) files (`-wal` and `-shm`).

!!! warning

    Make sure the connection to database is closed first!

=== "kotlin"

    ```{.kotlin .copy}
    storage.files.delete()
    ```

=== "swift"

    ```{.swift .copy}
    storage.files.delete()
    ```

### File size

Returns the size of a selected database file in bytes. If the file does not exist or cannot be read for some
reason, returns `null`.

!!! info

    Close the database connection first, to get the most accurate results.


=== "kotlin"

    ```{.kotlin .copy}
    val size: Long = storage.files.size(DatabaseFileType.Wal) ?: return
    ```

=== "swift"

    ```{.swift .copy}
    guard let fileSize = storage.files.size(type: .wal) else {
        return
    }
    let size = Int64(truncating: fileSize)
    ```


### DatabaseFileType

The `DatabaseFileType` enum defines the types of database files. Refer
to [SQLite WAL Format](https://www.sqlite.org/walformat.html) for more information on the WAL
file format.

- `Main`: The main database file.
- `Wal`: Write-Ahead Logging (WAL) file.
- `Index`: Index file.



