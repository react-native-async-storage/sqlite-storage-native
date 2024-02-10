//
//  OperationsView.swift
//  iosExample
//
//  Created by Krzysztof Borowy on 07/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import AsyncStorageSQLiteKMP


struct BasicCRUDView: View {
    internal var db: AsyncStorageSQLite
    
    @State var value: String?
    @State var timelineChanges: [String?] = []
    
    init(db: AsyncStorageSQLite) {
        self.db = db
        self.value = nil
    }
    
    func saveRandom() {
        let entry = Entry(key: "random", value: String(Int.random(in: 1...100)))
        Task {
            try await db.write(entry: entry)
        }
    }
    
    func readRandom() {
        Task {
            let result = try await db.read(key: "random")
            value = result.value
        }
    }
    
    func deleteRandom() {
        Task {
            try await db.remove(key: "random")
        }
    }
    
    func onAppear() async {
        print("on appear")
        do {
            for try await updates in db.readAsFlow(keys: ["random"]) {
                print("updated for \(updates)")
                let result = try await db.read(key: "random")
                timelineChanges.append(result.value ?? nil)
            }
        } catch {
            print("error \(error)")
        }
    }
    
    
    var body: some View {
        VStack {
            HStack {
                Button("Save", action: saveRandom)
                    .padding()
                    .border(/*@START_MENU_TOKEN@*/Color.black/*@END_MENU_TOKEN@*/)
                Button("Read", action: readRandom)
                    .padding()
                    .border(/*@START_MENU_TOKEN@*/Color.black/*@END_MENU_TOKEN@*/)
                Button("Remove", role: .destructive, action: deleteRandom)
                    .padding()
                    .border(/*@START_MENU_TOKEN@*/Color.black/*@END_MENU_TOKEN@*/)
            }.frame(maxWidth: .infinity).padding()
            
            (Text("value: ") + Text(value ?? "nil").fontWeight(.bold))
            VStack {
                Text("value changes in time:").fontWeight(.bold)
                ForEach(0..<timelineChanges.count, id: \.self) { index in
                    Text("Updated to \(timelineChanges[index] ?? "nil")")
                }
            }.padding()
        }.task {
            let _ = await onAppear()
        }
    }
}



#Preview {
    BasicCRUDView(db: AsyncStorageSQLite(name: "preview"))
}
