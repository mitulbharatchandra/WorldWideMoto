import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        KoinKt.doInitKoinMitul()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
