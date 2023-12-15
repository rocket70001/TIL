# 클래스 초기화

## 핵심📦
스위프트의 클래스에는 지정 생성자와 간편 생성자가 있다.   
지정 생성자는 속성값을 전부 초기화해야 하는 생성자이다.   
간편 생성자는 속성을 전부 초기화해야 하는 지정 생성자의 속성값에 미리 몇 개의 값을 두고 지정 생성자를 호출하는 생성자이다.(생성자를 호출하는 생성자 -> 편의 생성자라고도 함)   

클래스 초기화 시에 간편 생성자가 지정 생성자를 호출하고, 지정 생성자는 자신의 부모 클래스의 생성자를 호출하는 식으로 초기화한다.
  
간편 -> 지정 -> 슈퍼  
  
## 지정 생성자와 간편 생성자
클래스는 생성자가 끝나기 전까지 슈퍼 클래스를 비롯한 모든 인스턴스의 속성이 초기화되어야 한다. 이를 위해 클래스에는 두 가지 생성자가 제공된다. 지정 생성자(designated initializer)와 간편 생성자(convenience initializer)가 그것이다.

지정 생성자는 클래스에게 있어 최우선의 생성자이다. 따라서 지정 생성자가 클래스에 지정된 모든 속성을 초기화한다. 슈퍼 클래스가 있다면 슈퍼 클래스의 생성자도 호출해 슈퍼클래스의 모든 값을 초기화한다. 모든 클래스는 최소한 한 개 이상의 지정 생성자를 가지고 있어야 한다. 몇몇 경우에는 슈퍼 클래스의 지정 생성자를 상속받는 것으로 만족된다.

간편 생성자는 보조적 생성자이다. 몇 개의 파라미터 값이 디폴트로 제공되는 지정 생성자를 호출하기 위해 사용한다. 

## 간편 생성자 구문
지정 생성자는 일반 생성자와 같은 형식으로 선언하지만 간편 생성자는 앞에 convenience 키워드가 붙는다. 

```swift
convenience init(<#parameters#>) {
   <#statements#>
}
```

지정 생성자와 간편 생성자의 관계를 간단하게 하기 위해 스위프트는 delegation call을 위한 다음의 세 가지 규칙을 생성자 사이에 적용한다.

1.	지정 생성자는 반드시 직속 슈퍼 클래스의 지정 생성자를 호출해야 한다.
2.	간편 생성자는 반드시 같은 클래스에서 다른 생성자를 호출해야 한다.
3.	간편 생성자는 종국에는 반드시 지정 생성자를 호출해야 한다.
 
그림으로 표현하면 위와 같다.

모든 지정 생성자는 결과적으로 반드시 슈퍼클래스의 지정 생성자를 호출하고 모든 간편 생성자는 결과적으로 반드시 지정 생성자를 호출한다.

## 2단계 초기화
스위프트의 클래스 초기화는 2단계에 걸쳐 진행된다. 첫 번째 단계에서는 클래스의 저장 속성들이 초기화되고 초기화가 끝나면 1단계가 확정된다. 이후 해당 인스턴스를 사용할 준비가 되었을 때 2단계가 진행되어 커스텀한 속성값들이 들어가게 된다. 1, 2단계는 연속적으로 진행된다.

## 왜 굳이 2단계로 나눠서 할까?
1단계가 완전히 끝나기까지 해당 인스턴스의 속성에 접근하는 것이 금지되기 때문에 초기화를 안전하게 진행할 수 있다. 또 다른 생성자에 의해 예기치 못하게 속성의 값이 수정되는 것을 방지할 수도 있다. 


## 2단계 초기화를 위한 4단계 안전검사
스위프트는 2단계 초기화를 에러 없이 진행하기 위해 4단계의 안전 검사를 수행한다.

1.	지정 생성자는 슈퍼 클래스의 생성자를 호출하기 전에 자신의 모든 속성에 대한 초기화를 완료해야 한다.
-	객체의 메모리는 해당 객체의 저장 속성이 모두 초기화된 후에야 완전히 초기화된 것으로 인식된다. 이 규칙을 만족시키기 위해 지정 생성자는 슈퍼 클래스 생성자를 호출하기 전에 반드시 자신의 모든 속성을 초기화해야 한다.
2.	지정 생성자는 상속한 속성에 새 값을 할당하기 전에 슈퍼클래스의 생성자를 호출해야 한다. 그렇게 하지 않으면 지정 생성자에 할당된 새 값이 슈퍼클래스의 생성자에 의해 슈퍼클래스의 것으로 대치된다.
3.	간편 생성자는 아무런 값도 할당하지 않고 우선 다른 생성자를 호출해야 한다. 그렇게 하지 않으면 간편 생성자가 할당한 속성 값이 지정 생성자에 의해 대치된다.
4.	생성자는 1차 생성 작업이 완료되기 전까지 어떤 인스턴스 메소드도 호출할 수 없고 어떤 인스턴스 속성 값도 읽을 수 없다. 또 self 키워드로 자신의 인스턴스 값을 호출하는 것도 안된다.  

클래스 초기화 단계를 보다 구체적으로 정리하면 다음과 같다.

1단계
-	지정 생성자나 간편 생성자가 호출된다.
-	새 클래스 인스턴스를 저장할 메모리가 할당된다. 아직 메모리가 초기화되지는 않는다.
-	지정 생성자가 모든 속성에 값이 할당되었는지 확인하고 나면 메모리가 초기화된다.
-	슈퍼 클래스에도 동일한 일을 시키기 위해 지정 생성자가 슈퍼 클래스의 생성자를 호출한다.
-	이 과정을 상속 클래스의 최상단에 도달할 때까지 반복한다.
-	상속 사슬의 최상단에 도달하면, 그리고 상속의 최상단 클래스의 모든 속성이 값을 갖고 있다는 게 확인되면, 인스턴스의 메모리는 완전하게 초기화된다. 여기까지 마치면 1단계가 완료된다.

2단계
-	다시 상속 사슬의 최상단으로 돌아가서, 각 사슬의 지정 생성자는 인스턴스를 커스터마이즈할 수 있는 옵션을 가진다. 생성자는 이제 self에 접근할 수 있고 스스로의 속성을 수정할 수도, 인스턴스 메소드를 호출할 수도 있다.
-	마지막으로 사슬의 간편 생성자가 스스로를 커스터마이즈할 옵션을 갖고 스스로를 세팅한다.(self, properties, instance method 접근 허용)
 
1단계는 아래에서 위로(간편 -> 지정 -> 슈퍼 지정), 2단계는 위에서 아래로(슈퍼 지정 -> 지정 -> 간편) 이뤄진다.

Initializer Inheritance and Overriding
스위프트는 서브 클래스가 슈퍼 클래스의 생성자를 디폴트로 상속받지 않는다. 이런 스위프트의 접근 방식은 보다 구체화된 서브 클래스가 슈퍼클래스로부터 단순한 생성자를 상속받거나 완전히 초기화되지 않은 불완전한 서브 클래스 인스턴스를 생성하는 일을 방지한다. 특정 상황에서는 슈퍼클래스의 생성자가 상속되지만 해당 생성자를 사용하기에 안전하고 적절한 상황이어야만 한다.

커스텀 서브클래스에 슈퍼 클래스가 가진 것과 동일한 하나 이상의 생성자를 제공하고 싶다면 서브 클래스 안에 그런 생성자를 구현하면 된다. 슈퍼 클래스의 지정 생성자에 해당하는 생성자를 서브 클래스에 구현하는 건 슈퍼클래스의 지정 생성자를 오버라이딩하는 것이다. 따라서 override 키워드를 서브 클래스의 생성자 앞에 붙여야 한다. 

```swift
class Vehicle {
    var numberOfWheels = 0
    var description: String {
        return "\(numberOfWheels) wheel(s)"
    }
}
let vehicle = Vehicle()
print("Vehicle: \(vehicle.description)")
// Vehicle: 0 wheel(s)

class Bicycle: Vehicle {
    override init() {
        super.init()
        numberOfWheels = 2
    }
}

let bicycle = Bicycle()
print("Bicycle: \(bicycle.description)")
// Bicycle: 2 wheel(s)
```

클래스 생성의 2단계에서 서브 클래스의 생성자가 커스텀 구현을 하지 않은 상태이고, 슈퍼 클래스의 지정 생성자가 인자가 없는 동기(synchronous) 생성자라면, 그리고 서브 클래스의 모든 속성에 값을 할당한 뒤라면, super.init() 을 명시하지 않아도 된다. 

```swift
class Hoverboard: Vehicle {
    var color: String
    init(color: String) {
        self.color = color
        // super.init() implicitly called here
    }
    override var description: String {
        return "\(super.description) in a beautiful \(color)"
    }
}

let hoverboard = Hoverboard(color: "silver")
print("Hoverboard: \(hoverboard.description)")
// Hoverboard: 0 wheel(s) in a beautiful silver
```

위 코드를 보면 호버보드의 속성인 color에 값을 할당하는 서브 클래스 생성자를 호출하기만 했는데 상위 클래스인 Vehicle의 numberOfWheels가 0으로 세팅되어 리턴됐음을 알 수 있다.

## 자동 생성자 상속
위에서 살펴본 것과 같이 서브 클래스는 슈퍼클래스의 생성자를 디폴트로 갖지 않는다. 하지만 몇몇 조건이 충족될 시 슈퍼클래스의 생성자는 자동으로 상속된다. 위에서 살펴본 바로는 많은 경우에 슈퍼 클래스의 생성자를 오버라이드할 필요가 없었다. 서브 클래스에 적용된 모든 속성에 디폴트 값을 할당했다고 가정했을 때, 다음과 같은 2가지 규칙이 적용된다.

1.	서브클래스에서 어떠한 지정 생성자도 정의하지 않으면, 서브클래스는 자동으로 슈퍼 클래스의 모든 지정 생성자를 상속받는다.
2.	서브클래스가 슈퍼클래스의 모든 지정 생성자를 제공하거나 구현하면 슈퍼클래스의 모든 간편 생성자를 자동으로 상속받는다. 

2번째 규칙을 만족하면 서브클래스의 간편 생성자로도 슈퍼클래스의 지정 생성자를 구현할 수 있다.

## 실제 지정 생성자와 자동 생성자의 동작
실제 지정 – 편의 – 자동 생성자가 위계 관계 안에서 어떻게 동작하는지 살펴보자.
서열의 최상단에 Food 클래스가 존재한다. Food 클래스는 base class로 음식의 이름을 저장하는 단순한 클래스이다. name이라는 하나의 변수를 가지며 문자열을 받으며 두 개의 생성자를 가진다.

```swift
class Food {
    var name: String
    init(name: String) {
        self.name = name
    }
    convenience init() {
        self.init(name: "[Unnamed]")
    }
}
```

 
푸드 클래스의 생성자 사슬을 표현한 그림이다.
클래스는 구조체와 달리 default memberwise properties를 가지지 않는다. 그래서 푸드 클래스는 하나의 name 인자를 받는 생성자를 제공한다. 이는 구체적인 name을 가진 새 Food 인스턴스를 만들 수 있게 한다. 

```swift
let namedMeat = Food(name: "Bacon")
// namedMeat's name is "Bacon"
```

Food 클래스는 상위 클래스가 없기 때문에 super.init()을 가지고 있지 않다. 

```swift
let mysteryMeat = Food()
// mysteryMeat's name is "[Unnamed]"
```

위 코드에서는 Food 클래스가 제공하는 간편 생성자로 인스턴스를 만든다. [Unnamed]를 place holder로 두어 init()을 호출하게 했다.

다음 클래스는 RecipeIngredient로 푸드 클래스를 상속해 레시피의 양에 해당하는 quantity 프로퍼티를 더한다.

```swift
class RecipeIngredient: Food {
    var quantity: Int
    init(name: String, quantity: Int) {
        self.quantity = quantity
        super.init(name: name)
    }
    override convenience init(name: String) {
        self.init(name: name, quantity: 1)
    }
}
```

 

RecipeIngredient는 우선 자신의 속성을 초기화 시키고 슈퍼 클래스의 지정 생성자를 호출해 슈퍼클래스에 name값을 넘겨 슈퍼 클래스를 초기화한다. RecipeIngredient의 간편 생성자는 이름을 전달받아 양을 1로 초기화하는 인스턴스를 만들어 양이 1인 여러 개의 인스턴스를 만들 때 코드 중복을 피한다. 

```swift
let oneMysteryItem = RecipeIngredient()
let oneBacon = RecipeIngredient(name: "Bacon")
let sixEggs = RecipeIngredient(name: "Eggs", quantity: 6)

class ShoppingListItem: RecipeIngredient {
    var purchased = false
    var description: String {
        var output = "\(quantity) x \(name)"
        output += purchased ? " ✔" : " ✘"
        return output
    }
}
```

위에서 쇼핑리스트 아이템은 자신의 속성을 모두 가지고 시작한다. 생성자를 설정하지 않기 때문에 purchased 는 초기 생성 시 항상 false이다. 이렇게 모든 값이 세팅되어 있기 때문에 ShoppingListItem은 자동으로 슈퍼클래스의 모든 지정, 편의 생성자를 상속받는다. 
 

따라서 다음과 같이 사용할 수 있다.

```swift
var breakfastList = [
    ShoppingListItem(),
    ShoppingListItem(name: "Bacon"),
    ShoppingListItem(name: "Eggs", quantity: 6),
]
breakfastList[0].name = "Orange juice"
breakfastList[0].purchased = true
for item in breakfastList {
    print(item.description)
}
// 1 x Orange juice ✔
// 1 x Bacon ✘
// 6 x Eggs ✘
```

Swift 공식 문서 클래스 생성자 파트 정리했던 부분입니다.
