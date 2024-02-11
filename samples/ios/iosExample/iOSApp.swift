import SwiftUI

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
            TabView {
                BasicPage()
                    .tabItem { Label("Basic", systemImage: "figure.walk.circle").labelStyle(.titleAndIcon) }
                MultiPage()
                    .tabItem { Label("Multi", systemImage: "person.3.sequence.fill").labelStyle(.titleAndIcon) }
                KeysPage()
                    .tabItem { Label("Keys", systemImage: "key").labelStyle(.titleAndIcon) }
                MergePage()
                    .tabItem { Label("Merge", systemImage: "arrow.triangle.merge").labelStyle(.titleAndIcon) }
            }
		}
	}
}
