import SwiftUI
import SqliteStorage

struct ContentView: View {
    let db = DbAccess()
    
    @MainActor
    func writeRandom() {
        Task.init {
            let key="random_test"
            let value = String(repeating: "x", count: Int.random(in: 1000...10000))
            let entry = Entry(key: key, value: value)
            
            try! await db.saveEntry(entry: entry)
            let result = try! await db.readEntry(key: key)
            print("Random result: \(result)")
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
        Task.init {
            try! await db.saveEntry(entry: Entry(key: "merge_test", value: obj1))
            let result = try! await db.mergeEntry(entry: Entry(key: "merge_test", value: obj2))
            print("Merge result: \(result)")
        }
    }
    
    var body: some View {
        VStack {
            Button("Insert random") {
                writeRandom()
            }
            Button("Print location") {
                db.printLocation()
            }
            Button("Drop database") {
                db.dropDatabase()
            }
            Button("Read db size") {
                db.getSize()
            }
            Button("Merge and print result") {
                mergeEntries()
            }
        }
    }
}
