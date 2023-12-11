import Foundation
import SqliteStorage


struct DbAccess {
    private let db: StorageAccess = SQLiteStorageFactory().create(dbName: "my_db")
    
    
    func saveEntry(entry: Entry) async throws -> Void {
        return try! await withCheckedThrowingContinuation { cont in
            db.write(entry: entry, completionHandler: {error in
                if let error = error {
                    cont.resume(throwing: error)
                } else {
                    cont.resume()
                }
            })
        }
    }
    
    func readEntry(key: String) async throws -> String? {
        return try! await withCheckedThrowingContinuation { cont in
            db.read(key: key, completionHandler: { entry, error in
                if let entry = entry {
                    cont.resume(returning: entry.value)
                }
                if let error = error {
                    cont.resume(throwing: error)
                }
            })
        }
    }
    
    func mergeEntry(entry: Entry) async throws -> Entry {
        return try! await withCheckedThrowingContinuation { cont in
            db.merge(entry: entry, completionHandler: {newEntry, error in
                if let entry = newEntry {
                    cont.resume(returning: entry)
                }
                if let error = error {
                    cont.resume(throwing: error)
                }
            })
        }
    }
    
    func printLocation() {
        print(db.getDbPath())
    }
    
    
    func dropDatabase() {
        db.dropDatabase(completionHandler: {result, err in
            let success = result == true
            print("db dropped \(success ? "successfully" : "without success")")
        })
    }
    
    func getSize() {
        db.getDbSize(completionHandler: {size, err in
            if let size = size {
                print("DB size is \(size) KB")
            }
        })
    }
    
    
}
