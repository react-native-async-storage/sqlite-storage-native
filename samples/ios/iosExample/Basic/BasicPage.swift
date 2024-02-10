import SwiftUI
import AsyncStorageSQLiteKMP


struct BasicPage: View {
    @State var database: AsyncStorageSQLite?
    
    init() {
        let db = AsyncStorageSQLite(name: "basic.db")
        self._database = State(initialValue: db)
    }
    
    func onChangeName(name: String) {
        database = AsyncStorageSQLite(name: name)
    }
    
    var body: some View {
        VStack {
            DatabaseSelector(db: self.$database, onSelect: onChangeName)
            if let database {
                VStack {
                    Divider()
                    BasicCRUDView(db: database)
                }.id(database.name)
                
            }
            Spacer()
        }
        
    }
}


#Preview {
    BasicPage()
}
