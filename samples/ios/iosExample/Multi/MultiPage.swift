import SwiftUI
import AsyncStorageSQLite


private let DatabaseName = "multiple-example.db"

struct MultiPage: View {
    private let db = AsyncStorageSQLite(name: DatabaseName)
    @State var currentEntries: [Entry] = []
    
    
    init() {}
    
    
    func addRandomEntry() {
        Task {
            var entries: [Entry] = []
            for _ in 1...Int.random(in: 2...6) {
                let randomKey = "key-\(Int.random(in: 1..<15))"
                let randomValue = "value-\(Int.random(in: 100..<1000))"
                entries.append(Entry(key: randomKey, value: randomValue))
            }
            try await db.writeMany(entries: entries)
        }
    }
    
    func removeRandom() {
        guard !currentEntries.isEmpty else {
            return
        }
        
        Task {
            var keys: [String] = []
            for _ in 0...min(1, Int.random(in: 0..<currentEntries.count)) {
                if let element = currentEntries.randomElement() {
                    keys.append(element.key)
                }
            }
            
            try await db.removeMany(keys: keys)
        }
        
    }
    
    func clearAll() {
        Task {
            try await db.clear()
        }
    }
    
    func onAppear() async {
        do {
            for try await keys in db.readKeysAsFlow() {
                let entries = try await db.readMany(keys: keys)
                currentEntries = entries.sortedEntries()
            }
        } catch {
            print("error reading keys: \(error)")
        }
        
    }
    
    
    var body: some View {
        VStack {
            HStack(spacing: 30) {
                Button("Add random", action: addRandomEntry)
                Button("Remove some", action: removeRandom)
                Button("Clear all", role: .destructive, action: clearAll)
            }
            Spacer()
            List {
                Section {
                    ForEach(currentEntries, id: \.key) { entry in
                        Text("\(entry.key) : ") + Text(entry.value ?? "nil").fontWeight(.bold)
                    }
                }
            }.listStyle(.grouped)
        }.task {
            await onAppear()
        }
    }
}


#Preview {
    MultiPage()
}



private extension [Entry] {
    func sortedEntries() -> [Entry] {
        return self.sorted {a, b in
            let aKey = Int(a.key.dropFirst(4))
            let bKey = Int(b.key.dropFirst(4))
            return aKey! <= bKey!
        }
    }
}
