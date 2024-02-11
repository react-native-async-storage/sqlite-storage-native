//
//  OperationsView.swift
//  iosExample
//
//  Created by Krzysztof Borowy on 07/02/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import AsyncStorageSQLiteKMP


private let KEY = "random"

struct BasicView: View {
    internal var db: AsyncStorageSQLite
    
    @State var value: String?
    @State var timelineChanges: [String?] = []
    
    init(db: AsyncStorageSQLite) {
        self.db = db
        self.value = nil
    }
    
    func saveRandom() {
        let entry = Entry(key: KEY, value: String(Int.random(in: 1...100)))
        Task {
            try await db.write(entry: entry)
        }
    }
    
    func readRandom() {
        Task {
            let result = try await db.read(key: KEY)
            value = result.value
        }
    }
    
    func deleteRandom() {
        Task {
            try await db.remove(key: KEY)
        }
    }
    
    func onAppear() async {
        do {
            for try await updates in db.readAsFlow(keys: [KEY]) {
                if let entry = updates.first(where: {$0.key == KEY}) {
                    timelineChanges.append(entry.value)
                }
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
            
            (Text("current value: ") + Text(value ?? "nil").fontWeight(.bold))
            
            List {
                Section {
                    ForEach(0..<timelineChanges.count, id: \.self) { index in
                        Text("Updated to \(timelineChanges[index] ?? "nil")")
                    }
                } header: {
                    Label(
                        title: { Text("value changes in time:").fontWeight(.bold) },
                        icon: { Image(systemName: "trash") }
                    ).onTapGesture {
                        timelineChanges = []
                    }
                    
                }
                
            }.listStyle(.grouped)
            
            
        }.task {
            let _ = await onAppear()
        }
    }
}



#Preview {
    BasicView(db: AsyncStorageSQLite(name: "preview"))
}
