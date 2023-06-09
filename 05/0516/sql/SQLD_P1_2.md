<h1> 과목1 - 2. 데이터 모델과 성능 </h1><br>

## 1절. 정규화
정규화는 데이터 모델링에서 가장 기초적이면서 필수적인 작업이다. 이번 장에서는 정규화의 필요성을 반드시 알아야 한다.   

1. 제1정규형 : 하나의 속성에 하나의 값
2. 제2정규형 : 부분 종속성 제거
함수 종속성은 어떤 기준값에 의해 데이터가 종속되는 현상을 지칭한다. 이때 기준값을 결정자라고 하고 종속되는 값을  
종속자라고 한다. 만약 특정 속성이 식별자 전체가 아닌 일부에만 종속적이면 이를 부분 종속이라고 한다. 일반속성이 주식별자가 아닌 다른 식별자에만 종속적인 부분 종속 상황이 생기면 제2정규형이 위배된다. 당장에는 큰 문제가 없을 수 있지만 가까운 미래에 주식별자가 아닌 타 식별자에 부분 종속적인 개체의 이름이 바뀔 경우, 이름이 바뀌기 이전 개체를 모두 찾아 바뀐 이름으로 변경해야 한다.  
이 경우 엔터티를 분리해 제2정규형을 만족시킬 수 있다.  
3. 제3정규형 : 이행 종속성 제거  
이행 종속성도 데이터 변경을 위해 제거한다. 데이터 변경으로 발생하는 트랜잭션은 도메인 로직과 관련없는 트랜잭션이기에 엔터티를 분리해 이행 종속성을 제거해야 한다.  
  
정규화는 기초적이지만 무조건적이지는 않다. 경우에 따라 성능 향상을 위해 반정규화를 진행하기도 한다.  
반정규화를 할 때는 성능향상도 좋지만 트레이드오프를 고려해야 한다.  
일례로 배송관련 시스템을 구축할 때 주문과 배송을 별개의 엔터티로 구성해 모델링을 진행했다고 가정해보자.  
고객의 주문 번호(주식별자)를 가지는 엔터티는 주문이며 배송 정보를 확인할 송장번호는 배송 엔터티에 들어있다. 
이런 경우에 고객이 매번 배송정보를 확인할 때마다 주문 번호가 있는 주문 엔터티와 주문번호를 외래키로 가지는 배송 엔터티를 조인해  
배송 엔터티 내의 송장번호를 확인해야 한다. 이를 해결하기 위해 송장 번호를 주문 엔터티에도 포함시켜 반정규화를 진행할 수 있다.  
그러나 송장 번호는 배송이 출발할 때 입력되는 정보이기에 고객이 주문을 한 시점에는 항상 NULL값이거나 디폴트 값으로 존재하게 된다.  
이 경우 주문 엔터티에는 이전에는 필요없던 UPDATE(송장 번호 갱신을 위한) 로직이 추가되어야 한다. 따라서 조회(SELECT) 성능향상을 위해 UPDATE 로직을 추가해야 하는 상황에 놓이게 된다. 이런 경우는 반정규화를 진행하지 않아야 한다.  
  
## 2절. 관계와 조인의 이해  
DB에서 관계를 맺는다는 건 식별자를 상속하고, 상속된 속성을 매핑키로 활용해 데이터를 결합해 볼 수 있다는 의미이다.  
SQL에서는 이를 조인이라고 한다.  
  
#### 조인
관계가 형성된 두 엔터티에서 관계를 잇는 키를 매핑키로 설정해 원하는 정보를 얻는 행위이다.  
  
#### 계층형 데이터 모델(셀프 조인)
자가 참조하는 모델. 스스로의 키를 계층 관계 내의 엔터티 모델에 재참조시켜 셀프 조인하는 모델이다.  
예로 엔지니어, 마케터, 매니저가 공통으로 존재하는 직원 엔터티가 있을 때 엔지니어와 마케터의 관리자를 찾는 쿼리를 구성하려 한다.  
이때 같은 엔터티 내의 매니저 코드를 재참조해 엔지니어와 마케터의 매니저를 특정할 수 있다.  
계층형 데이터 모델은 일반적으로 사용되며 계층형 쿼리(Connect By절)을 이해하기 위해 반드시 알고 있어야 한다.  
  
#### 상호배타적(Exclusive-OR 관계)
1:N 관계를 맺는 엔터티(1)이지만 N 중 하나로부터만 상속하는 관계를 말한다.  
ex)회원 엔터티는 개인 엔터티와 법인 엔터티와 동시에 관계를 맺지만 동시 상속은 안 된다.

## 3절. 모델이 표현하는 트랜잭션의 이해
트랜잭션은 데이터베이스의 논리적 연산단위이다. 계좌이체를 할 때 송신인의 계좌에서 송금액 만큼의 돈을 차감하고 수신인의 계좌에 송금액 만큼의 돈을 가산하는 일련의 과정은 한 트랜잭션이라고 할 수 있다.  
이런 업무적인 트랜잭션만 있는 것은 아니다. 모델에도 트랜잭션이 발생할 수 있다. 또한 데이터 모델링 진행 시에도 트랜잭션을 표현할 수 있다.  
이렇게 만들어낸 트랜잭션은 ACID 중 하나인 Atomicity를 지켜야한다. 따라서 트랜잭션 내부의 쿼리문이 전부 실행되든지 전부  
실행되지 않든지 중 하나여야 한다. 논리 연산을 수행하고 이를 기록하는 commit(); 작업은 따라서 트랜잭션 내의 모든 쿼리문이  
수행된 이후에 행해지는 것이 일반적이다.  
프레임워크에서 autocommit 기능 지원으로 대부분 커밋에 대한 이런 부분을 간과하지만, 잘못된 트랜잭션 처리는 데이터 정합성  
문제를 야기하고 이는 데이터 품질을 저하시키므로 염두해야 하는 부분이다.  

## 4절. Null 속성에 대한 이해 
IE표기법에서는 Null 속성이 가능한지 알 수 없지만 바커 표기법에서는 속성 앞에 있는 동그라미가 Null 허용 속성임을 으미한다.  
데이터 베이스 상 Null 값에는 몇 가지 특성이 존재한다.  
  
1. Null 값의 연산은 언제나 Null이다.  
DB에서 Null은 공백 또는 숫자 0과 전혀 다른 의미이다. 대부분은 '아직 정의되지 않은 값' 또는  
'현재 데이터를 입력하지 못하는 경우'를 의미한다. 따라서 Null이 들어간 연산을 하게 되면 얻게 되는 결과는 언제나  
IS NULL이거나 IS NOT NULL이다. 숫자를 더한다거나 문자열을 합쳐도 NULL이 섞이면 전과 같은 결과가 나오게 된다.  
그러나 데이터베이스의 크기가 커질 수록 인스턴스가 Null을 가지는지 모두 확인하고 연산할 수는 없다.  
이때 NVL함수를 사용해 NVL(Nullabe속성, 0)과 같이 설정하면 해당 속성값이 Null일 경우 자동으로 두 번째 파라미터인 0을 Null  
대신 값으로 치환해 계산하게 된다. 이처럼 Null 연산의 결과는 언제나 Null이라는 속성을 숙지하고 잘못된 결과를  
반환하지 않도록 해야 한다.  
  
2. 집계함수는 Null값을 제외하고 처리한다.  
AVG()와 같은 집계함수는 값 중에 Null값이 있으면 이를 무시하고 진행한다. 아예 개수로 카운팅도 하지 않는다.  
예를 들어 SUM(\*) / COUNT(\*)와 같은 식으로 평균을 내면 SUM()은 Null을 무시하고 합을 계산하지만 COUNT는 값이 아닌 행의 개수를  
카운팅해 AVG()와 SUM(\*) / COUNT(\*)는 다른 값을 가질 수 있다.  

## 5절. 본질식별자 vs 인조식별자
본질식별자: 업무에 의해 만들어진 식별자  
인조식별자: 업무적으로 만들어지지는 않지만 본질식별자가 복잡한 구성을 갖고 있어 인위적으로 만든 식별자  
인조식별자는 본질식별자를 상속한 엔터티에서 중복(중복된 주문 등의 카운팅이 필요한 중복)되는 데이터 계산, 또는 INSERT를  
편리하게 하기 위해 설정한다. 인조식별자를 지정함으로써 해당 식별자를 단일식별자화 할 수 있고 단일식별자로 구성된 키값은  
시퀀스 객체로 만들어 SEQ.NEXTVAL등을 활용해 작업량을 줄일 수 있다. 그러나 다음과 같은 문제점이 생긴다.  
- 중복 데이터로 인한 품질 문제  
- 불필요한 인덱스 생성  
  
1. 중복 데이터로 인한 품질 문제  
위에서 설명했듯 인조식별자는 중복되는 데이터를 관리하기 위해 주로 사용된다. 따라서 인조식별자를 사용하면 중복 데이터를 막을 수 없다.  
이는 로직 오류로 인해 발생한 중복을 제거하지 못함을 의미한다. 만약에 로직 오류로 같은 INSERT문이 두 번 들어가게 되었다 하더라도 인조식별자를 시퀀스 처리해 삽입했다면 제약에 위배 사항이 없기에 그대로 적용된다.  
  
2. 불필요한 인덱스 생성  
데이터에 엑세스할 때 본질식별자로 구성된 엔터티와 달리 인조식별자가 섞인 엔터티에서는 인조 식별자에 대한 인덱스를 추가로  
생성해야한다. 추가로 생성한 인덱스는 용량과 DML 성능에 영향을 줄 수 있음을 염두해야 한다.  
  
인조식별자를 사용할 때는 위와 같이 사용을 통해 얻는 업무 효율 향상과 인덱스 증가, 중복을 막을 수 없음 등의 장단점을 따져봐야 한다.  

