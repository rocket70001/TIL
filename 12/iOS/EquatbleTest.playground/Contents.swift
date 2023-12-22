import UIKit
import SwiftUI

var greeting = "Hello, playground"

struct Something : Equatable {
    var fir = ""
    var sec = 2
    var third = true
    static func == (lhs: Something, rhs: Something) -> Bool {
        return lhs.fir == rhs.fir
}
}

let something1 = Something.init(fir: "", sec: 2, third: true)
let something2 = Something.init(fir: "", sec: 7, third: false)

something1 == something2 // true
