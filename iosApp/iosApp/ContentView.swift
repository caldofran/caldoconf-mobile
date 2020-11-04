import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        let event = Event(identifier: "12345", motto: "Hello biatches")
        Text(event.motto!)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
