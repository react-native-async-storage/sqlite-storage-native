import SwiftUI
import AsyncStorageSQLiteKMP


struct MergePage: View {
    
    private let db = AsyncStorageSQLite(name: "merge.sqlite")
    private let defaultEntry: Entry
    
    @State var mergeValue = createMergingValue()
    @State var merged: String? = nil
    
    
    init() {
        defaultEntry = Entry(key: "default", value: defaultValue)
    }
    
    
    func randomizeMergeValue() {
        mergeValue = createMergingValue()
    }
    
    func reset() {
        merged = nil
        Task {
            try await db.write(entry: defaultEntry)
        }
    }
    
    func mergeValues() {
        Task {
            let updated = try await db.merge(entry: Entry(key: defaultEntry.key, value: mergeValue))
            merged = updated.value
        }
    }
    
    
    var body: some View {
        ScrollView {
            VStack {
                HStack(spacing: 20) {
                    Button("randomize", action: randomizeMergeValue)
                    Button("merge", role: .cancel, action: mergeValues)
                    Button("reset", role: .destructive, action: reset)
                }
                
                HStack {
                    Text(defaultEntry.value!.prettyJSON).font(.caption)
                    Divider()
                    Text(mergeValue.prettyJSON).font(.caption)
                }
                Spacer()
                if let merged = merged?.prettyJSON {
                    Divider()
                    Text("Merge result:").font(.headline)
                    Text(merged).font(.caption)
                }
                
            }
        }
    }
}


private let defaultValue = """
{
   "index":0,
   "isActive":false,
   "age":29,
   "name":"Johanna Sykes",
   "tags":[
      "tag-0",
      "tag-1",
      "tag-2"
   ],
   "properties":{
      "topIndex":0,
      "inner":false,
      "innerList":[
         1,
         2,
         3
      ],
      "innerProp":{
         "keep":"me",
         "name":"original"
      }
   }
}
"""

private func createMergingValue() -> String {
    let index = { Int.random(in: 0...5) }
    let boolean = { Bool.random() }
    let tags = {
        var t: [String] = []
        for _ in 0..<3 {
            t.append("tag-\(Int.random(in: 0...6))")
        }
        return t
    }
    let randomInt = { Int.random(in: 10...20)}
    
    return """
{
  "index": \(index()),
  "isActive": \(boolean()),
  "age": 21,
  "address": "\(Int.random(in: 4...9))th street",
  "tags": \(tags()),
  "properties": {
    "topIndex": \(index()),
    "inner": \(boolean()),
    "innerList": [
      \(randomInt()),
      \(randomInt()),
      \(randomInt())
    ],
    "innerProp": {
      "name": "overridden",
      "newProp": true
    }
  }
}
"""
}

extension String {
    var prettyJSON: String {
        let data = self.data(using: .utf8)!
        guard let object = try? JSONSerialization.jsonObject(with: data, options: []),
              let data = try? JSONSerialization.data(withJSONObject: object, options: [.prettyPrinted]),
              let prettyPrintedString = NSString(data: data, encoding: String.Encoding.utf8.rawValue) else { return "" }
        
        return String(prettyPrintedString)
    }
}
