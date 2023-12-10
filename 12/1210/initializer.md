## 초기화와 초기화 위임(Initializer Delegtion)

### 초기화의 종류
#### 1. 디폴트 초기화 
옵셔널 타입을 제외한 클래스나 구조체의 저장 속성(stored properties)은 생성 시점에 반드시 초기화된 값을 가져야 합니다.  
```swift
struct Farenheit {
    var temperature: Double
    init() {
        temperature = 32.0
    }
}

var f = Farenheit()
print("The default temperature is \(f.temperature)degree Farenheit")
// Prints "The default temperature is 32.0degree Farenheit"
```
위에서는 init(){temperature = 32.0}으로 한 개의 변수에 값을 할당했습니다.  
하지만 위 상황처럼 저장 속성이 항상 같은 값으로 초기화된다면 굳이 생성자를 만들 필요가 없습니다.  
구조체를 정의하면서 초기 속성값을 할당하면 되기 때문입니다.  

```swift
struct Farenheit {
    var temperature = 32.0
}
```
이렇게 되면 디폴트 생성자가 생성되어 뒤에 나올 초기화 위임시에 더 깔끔한 코드를 작성할 수 있게 합니다.  
옵셔널 변수가 포함된 경우여도 옵셔널을 제외한 다른 모든 속성에 값이 할당되어 있다면 디폴트 초기화가 가능합니다.  

```swift
class ShoppingListItem {
    var name: String?
    var quantity = 1
    var purchased = false
}

var item = ShoppingListItem()
```
옵셔널 타입 name은 자동으로 nil로 초기화 됩니다.

#### 2. 커스텀 초기화
만약 저장 속성의 값이 고정된 초깃값이 아니라 새로 할당된 속성을 갖는다면 생성자에 이를 명시해 커스텀 초기화를 
할 수 있습니다.  
```swift
struct Color {
    let red, green, blue: Double

    init(red: Double, green: Double, blue: Double) {
        self.red = red
        self.green = green
        self.blue = blue
    }

    init(white: Double) {
        red = white
        green = white
        blue = white
    }
}

let magenta = Color(red: 1.0, green: 0.0, blue: 1.0)
let halfGrey = Color(white: 0.5)
//let veryGreen = Color(0.0, 1.0, 0.0)
//인자가 있는 생성자에 인자를 입력하지 않고 값만 넣으면 컴파일 에러가 발생합니다.
```

스위프트의 초기화가 다른 언어와 구별되는 특징은 argument label을 달리해 같은 타입을 입력하지만 다른 결과를 낳는 초기화 방식을 사용한다는 점입니다.  
```swift
struct Celcius {
    var temperatureInCelcius: Double

    init(fromFarenheit farenheit: Double) {
        temperatureInCelcius = (farenheit - 32.0) / 1.8
    }

    init(fromKelvie Kelvin: Double) {
        temperatureInCelcius = kelvin - 273.15
    }

    init(_ celcius: Double) {
        temperatureInCelcius = celcius
    }

}
```
다른 많은 언어에서는 타입과 파라미터의 개수가 동일한 생성자로 다른 인스턴스를 생성할 수 없습니다.  
하지만 스위프트는 argument label를 활용해 다양한 커스텀 생성자를 만들 수 있습니다.  

#### 3. 구조체만의 Memberwise(멤버별) 초기화
앞서 디폴트 초기화를 사용하려면 인스턴스의 모든 속성에 값이 할당되어 있어야 한다고 했습니다.  
하지만 특별하게 구조체는 모든 값이 할당되어 있지 않아도 초기화할 수 있는 멤버와이즈 초기화가 지원됩니다.  

```swift
struct Size {
    let nope = "This constant isn't going to be initialized"
    var width = 0, height = 0
    var name: String?
}

//let으로 선언되어 이미 초기화가 되어있는 defaultLet은 초기화에서 제외됩니다.
//만약 let으로 선언되어 초기화되어 있지 않다면 생성자에서 값을 넣어주고 해당 값이 상수로 고정됩니다.
//멤버와이즈 초기화를 통해 별도로 생성자를 선언하지 않았지만 속성명을 인자로 사용해 초기화할 수 있습니다.
let nothingButAlright = init()
let widthAndHeight = init(width: 1.0, height: 1.0)
let zeroByTwo(width: 0.0, height: 2.0, name: "newRectangle")
//하지만 멤버와이즈 초기화는 반드시 속성명의 순서를 따라야 합니다. 그렇지 않으면 컴파일 에러가 발생합니다.
let errorSize(height: 2.0, width: 0.0, name: "Error") //Error!
```

#### 멤버와이즈 주의사항🚨
```swift
struct NoMore {
    var noMoreMemberwies: String
    var nothing: String

    init(param: String) {
        self.noMoreMemberwies = param
        self.nothing = parma
    }
}

//error: missing argument for parameter 'param' in call
NoMore.init(noMoreMemberwies: "error", nothing: "error")
```
생성자를 직접 생성하면 Memberwise Initializer가 제공되지 않습니다.

```swift
struct NoPrivate {
    private var name: String
    var normal: String
}

//Error!
NoPrivate(name: "aa", normal: "bb")
```
프로퍼티 중 private이 있으면 Memberwise Initializer가 제공되지 않습니다.
  
## 값 타입에 적용되는 초기화 위임
  
값타입(구조체, 열거형)에서는 생성자가 자기 속성에 값을 할당하기 위해 다른 생성자를 호출할 수 있습니다.  
클래스에서는 상속이 존재하기 때문에(슈퍼 클래스와의 관계도 생각해서) 초기화 위임이 조금 더 복잡합니다.  
오늘은 값타입까지만 다루겠습니다.
  
값타입은 단순히 self.init()을 선언하면 되기 때문에 비교적 간단합니다. 
```swift
struct Rect {
    var origin = Point()
    var size = Size()
    
    init() {}

    init(origin: Point, size: Size) {
        self.origin = origin
        self.size = size
    }

    init(center: Point, size: Size) {
        let originX = center.x - (size.width / 2)
        let originY = center.y - (size.height / 2)
        //값타입의 초기화 위임 호출
        self.init(origin: Point(x: originX, y: originY), size: size)
    }
}

//위에서 복습했던 초기화 생성 방식 중 디폴트 초기화에 해당합니다.
let basicRect = Rect()
// -> basicRect의 origin은 (0.0, 0.0)으로, size는 (0.0, 0.0)으로 초기화됩니다.

//두번째 초기화 방식을 사용해 인스턴스를 있는 그대로 프로퍼티에 할당합니다.
let originRect = Rect(origin: Point(x: 2.0, y: 2.0),
        size: Size(width: 5.0, height: 5.0))
// -> originRect의 origin은 (2.0, 2.0)으로, size는 (5.0, 5.0)으로 초기화됩니다.

//Argument Label을 변경해 같은 속성 인스턴스로 다른 결과를 만듭니다.
//세번째 초기화 방식을 따라 자기 생성자 안에서 다른 구조체의 생성자를 호출해 새로운 값의 인스턴스를 만듭니다.
let centerRect = Rect(center: Point(x: 2.0, y: 2.0),
        size: Size(width: 5.0, height: 5.0))
// -> centerRect의 origin은 (-0.5, -0.5)로, size는 (5.0, 5.0)으로 초기화됩니다.
```
