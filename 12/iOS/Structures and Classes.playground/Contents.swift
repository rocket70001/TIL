import UIKit

struct Resolution {
    var width = 0
    var height = 0
}

class VideoMode {
    var resolution = Resolution()
    var interlaced = false
    var frameRate = 0.0
    var name: String?
}

let someResolution = Resolution()
let someVideoMode = VideoMode()

print("The width of someResolution is \(someResolution.width)")

let vga = Resolution(width: 640, height: 480)
print(String(vga.width) + " " + String(vga.height)) // 이런 형태의 memberwise initializer는 구조체만 제공, 클래스는 안됨

let hd = Resolution(width: 1920, height: 1080)
var cinema = hd

// 클래스는 참조형
let tenEighty = VideoMode()
tenEighty.resolution = hd
tenEighty.interlaced = true
tenEighty.name = "1080i"
tenEighty.frameRate = 25.0

let alsoTenEighty = tenEighty
alsoTenEighty.frameRate = 30.0

print("The frameRate property of tenEighty is now \(tenEighty.frameRate)")
