import SwiftUI
import AsyncStorageSQLiteKMP

struct DatabaseSelector: View {
    @Binding private var db: AsyncStorageSQLite?
    private var onSelect: (String) -> Void
    
    @FocusState private var focused: Bool
    @State private var name: String
    
    init(db: Binding<AsyncStorageSQLite?> = Binding.constant(nil), onSelect: @escaping (String) -> Void) {
        self._db = db
        self.onSelect = onSelect
        self.name = db.wrappedValue?.name ?? ""
        self.focused = false
    }
    
    
    func finish() {
        onSelect(name)
        focused = false
    }
    
    var body: some View {
        let dbName = db?.name ?? ""
        
        VStack(spacing: nil) {
            HStack {
                TextField("Database name", text: $name, onCommit: finish)
                    .padding()
                    .border(/*@START_MENU_TOKEN@*/Color.black/*@END_MENU_TOKEN@*/, width: /*@START_MENU_TOKEN@*/1/*@END_MENU_TOKEN@*/)
                    .focused($focused)
                Button("Select", action: finish)
            }
            Text("database: ") + Text(dbName).fontWeight(.bold)
        }.padding()
    }
}
