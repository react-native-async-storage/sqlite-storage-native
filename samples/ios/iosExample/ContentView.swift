import SwiftUI
import AsyncStorageSQLiteKMP

struct ContentView: View {
    let db = AsyncStorageSQLite(name: "example.db")
    let randomKey = "random"
    
    
    func writeRandom() {
        Task {
            let value = String(Int.random(in: 1...1000))
            let entry = Entry(key: randomKey, value: value)
            print("saving random value")
            try! await db.write(entry: entry)
        }
    }
    
    func readRandom() {
        Task {
            let value = try! await db.read(key: randomKey)
            print("random value read: \(value)")
        }
    }
    
    func readEntriesAsAsyncSequence() {
        Task {
            for try await keys in db.readAsFlow(keys: ["random"]) {
                print("entries are: \(keys)")
            }
        }
    }
    
    @MainActor
    func mergeEntries() {
        let obj1 = """
            {"a":123,"b":true,"c":[0,1,2,3],"d":null,"e":{"e1":true,"e2":null,"e3":["a","b","c"]}}
        """
        let obj2 = """
            {"a":987,"c":[4,5,6],"e":{"e1":false,"e3":888},"f":"new_entry"}
        """
        // merged: {"a":987,"b":true,"c":[0,1,2,3,4,5,6],"d":null,"e":{"e1":false,"e2":null,"e3":888},"f":"new_entry"}
//        Task.init {
//            try! await db.saveEntry(entry: Entry(key: "merge_test", value: obj1))
//            let result = try! await db.mergeEntry(entry: Entry(key: "merge_test", value: obj2))
//            print("Merge result: \(result)")
//        }
    }
    
    var body: some View {
        VStack {
            Button("Insert random") {
                writeRandom()
            }
            Button("Read random") {
                readRandom()
            }
            Button("Print location") {
                db.files.path(type: .main)
            }
            Button("read keys as flow") {
                readEntriesAsAsyncSequence()
            }
            Button("Drop database") {
                Task {
                    try await db.closeConnection()
                }
            }
            Button("Read db size") {
                print("db size: \(db.files.size(type: .main))")
            }
            Button("Merge and print result") {
                mergeEntries()
            }
        }
    }
}
