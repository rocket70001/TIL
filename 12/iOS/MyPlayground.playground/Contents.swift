import UIKit


func incrementBy(forInner amount: Int) -> () -> Int {
    var total = 0
    func inner() -> Int {
        total += amount
        return total
    }
    return inner
}

let incrementByTen = incrementBy(forInner: 10)
incrementByTen()
incrementByTen()
incrementByTen()
