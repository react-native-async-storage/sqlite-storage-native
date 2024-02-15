import SQLiteStorageKMP
import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCore

public typealias Entry = EntryKMP
public typealias Unit = KotlinUnit
public typealias StorageAsyncSequence<T, E: Error> = NativeFlowAsyncSequence<T, E, Unit>


public struct AsyncStorageSQLite {
    
    public let name: String
    public let files: DatabaseFiles
    private let storage: SQLiteStorageKMP
    
    public init(name: String) {
        self.name = name
        self.storage = SQLiteStorageFactoryKMP().create(dbName: name)
        self.files = self.storage.files
    }
    
    
    // MARK: reading from storage
    public func read(key: String) async throws -> Entry {
        let readFunc: ReturnFunc<Entry> = SQLiteStorageNativeKt.read(self.storage, key: key)
        return try await asyncFunction(for: readFunc)
    }
    
    public func readMany(keys: [String]) async throws -> [Entry] {
        let readManyFunc: ReturnFunc<[Entry]> = SQLiteStorageNativeKt.readMany(self.storage, keys: keys)
        
        return try await asyncFunction(for: readManyFunc)
    }
    
    public func readAsFlow(keys: [String]) -> StorageAsyncSequence<[Entry], Error> {
        let nativeFlow: NativeFlow = SQLiteStorageNativeKt.readAsFlow(self.storage, keys: keys)
        let entrySequence: NativeFlowAsyncSequence<[Entry], Error, Unit> = asyncSequence(for: nativeFlow)
        
        return entrySequence
    }
    
    
    // MARK: writing to storage
    public func write(entry: Entry) async throws {
        let writeFunc: NoReturnFunc = SQLiteStorageNativeKt.write(self.storage, entry: entry)
        
        
        _ = try await asyncFunction(for: writeFunc)
    }
    
    public func writeMany(entries: [Entry]) async throws {
        let writeManyFunc: NoReturnFunc = SQLiteStorageNativeKt.writeMany(self.storage, entries: entries)
        _ = try await asyncFunction(for: writeManyFunc)
    }
    
    // MARK: removing from storage
    public func remove(key: String) async throws {
        let removeFunc: NoReturnFunc = SQLiteStorageNativeKt.remove(self.storage, key: key)
        _ = try await asyncFunction(for: removeFunc)
    }
    
    public func removeMany(keys: [String]) async throws {
        let removeFunc: NoReturnFunc = SQLiteStorageNativeKt.removeMany(self.storage, keys: keys)
        _ = try await asyncFunction(for: removeFunc)
    }
    
    // MARK: merging entries
    public func merge(entry: Entry) async throws -> Entry {
        let mergeFunc: ReturnFunc<Entry> = SQLiteStorageNativeKt.merge(self.storage, entry: entry)
        
        return try await asyncFunction(for: mergeFunc)
    }
    
    public func mergeMany(entries: [Entry]) async throws -> [Entry] {
        let mergeFunc: ReturnFunc<[Entry]> = SQLiteStorageNativeKt.mergeMany(self.storage, entries: entries)
        
        return try await asyncFunction(for: mergeFunc)
    }
    
    // MARK: keys reading
    public func readKeys() async throws -> [String] {
        let readKeysFunc: ReturnFunc<[String]> = SQLiteStorageNativeKt.readKeys(self.storage)
        return try await asyncFunction(for: readKeysFunc)
    }
    
    public func readKeysAsFlow() -> StorageAsyncSequence<[String], Error> {
        let nativeFlow: NativeFlow = SQLiteStorageNativeKt.readKeysAsFlow(self.storage)
        let sequence = asyncSequence(for: nativeFlow)
        return sequence
    }
    
    // MARK: clearing storage
    public func clear() async throws {
        let clearFunc: NoReturnFunc = SQLiteStorageNativeKt.clear(self.storage)
        _ = try await asyncFunction(for: clearFunc)
    }
    
    
    public func closeConnection() {
        self.storage.closeConnection()
    }
}


private typealias ReturnFunc<T> = NativeSuspend<T, Error, Unit>
private typealias NoReturnFunc = NativeSuspend<Unit, Error, Unit>
private typealias SequenceFunc<T> = NativeFlowAsyncSequence<T, Error, Unit>
