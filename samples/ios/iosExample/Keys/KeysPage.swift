import SwiftUI
import AsyncStorageSQLite

struct KeysPage: View {
    
    let db = AsyncStorageSQLite(name: "keys.db")
    
    @State var keys: [String] = []
    
    init() {}
    
    func addRandomKey()  {
        let key = "key-\(Int.random(in: 1...10000))"
        Task {
            let entry = Entry(key: key, value: "random-value-\(key)")
            try await db.write(entry: entry)
        }
    }
    
    
    func onAppear() async {
        do {
            for try await updated in db.readKeysAsFlow() {
                keys = updated
            }
        } catch {
            print("error reading keys \(error)")
        }
    }
    
    func onDelete(_ index: Int) {
        let key = keys[index]
        Task {
            try await db.remove(key:key)
        }
    }
    
    var body: some View {
        VStack {
            Button("Add entry", action: addRandomKey)
            
            List {
                Section {
                    ForEach(0..<keys.count, id: \.self) { index in
                        Label(keys[index], systemImage: "trash").imageScale(.small)
                            .onTapGesture(perform: {
                                onDelete(index)
                            })
                    }
                } header: {
                    Text("Keys available")
                }
            }
        }.task {
            await onAppear()
        }
    }
}


#Preview {
    KeysPage()
}
