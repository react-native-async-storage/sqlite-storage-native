import SwiftUI

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
            TabView {
                BasicPage()
                    .tabItem { Label("Basic", systemImage: "figure.walk.circle").labelStyle(.titleAndIcon) }
            }
		}
	}
}
