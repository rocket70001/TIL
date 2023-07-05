# Spring0705

## JDBC
Java Database Connectivity는 자바에서 DB에 접속하도록 돕는 자바 API이다. 
DB벤더(DB회사)는 각자 JDBC에 맞게 SQL을 인식하는 드라이버를 만들어야 한다. JDBC는 각 DB벤더의 구현체를 사용하는 인터페이스가 된다. 
JDBC의 등장으로 사용자는 DB마다 조금씩 상이한 부분을 코드에 따로 적용할 필요가 없어졌다. 또 애플리케이션의 DB를 변경할 때 DB와 관련된 쿼리를 모두 바꿔야 하는 수고도 덜 수 있었다. 
하지만 현실적으로 ANSI 표준이 지원하지 못하는 범위의 쿼리들도 많아 특정 SQL은 다시 해당 DB에 맞게 변경해야 한다는 한계가 있었다.<br>
  
## SQL Mapper와 ORM
위 JDBC를 편리하게 사용하기 위해 나온 기술이 SQL Mapper와 ORM 기술이다. 
SQL Mapper는 JDBC의 반복되는 코드를 줄여주고 SQL 응답결과를 객체로 편리하게 반환하도록 돕는 기술이다. 대표적으로 JDBC Template와 MyBatis가 있다. 
ORM은 Object Relational Mapping으로 자바의 객체지향 패러다임을 그대로 이용해 관계형 DB를 다룰 수 있게 돕는다. 대표적으로 JPA가 있다.<br>
  
## CRUD와 HTTP 메서드
CRUD는 일반적으로 HTTP의 Post, Get, Put, Delete 메서드로 구현된다. 
@RequestMapping으로 url을 매핑해주고 같은 url 아래 HTTP 메서드로 매핑된 메서드를 만들면(@GetMapping, @PostMapping ...) 
같은 url에서 다른 HTTP 메서드로 동작하는 메서드를 만들 수 있다.

## @PostConstruct
컴포넌트로 등록된 빈이 생성되고 의존성 주입이 된 시점에서 실행되는 메서드를 지정할 수 있다. init()메서드를 @PostConstruct로 두는 경우가 많다. 
해당 init에는 일반적으로 반드시 필요한 초기화 작업들(디폴트값 설정, 리소스 구성)등이 포함된다. 
수동으로 실행해야 하는 작업들을 스프링 빈 생성과 동시에 자동 실행시키고 싶을 때 @PostConstruct를 사용하면 유지보수성이 좋고 중복성이 낮은 코드를 작성할 수 있다.<br>

## DTO(Data Transfer Object)
엔티티 외에 별도의 데이터 집합인 DTO를 만들어 쓴다. 엔티티는 전체 집합이고 DTO는 필요한 부분만 가져다 쓰는 부분 집합이라고 생각하면 된다. 
DTO를 사용하면 다양한 이점이 있다. 캡슐화(불필요한 데이터 노출 x), 유지보수성, 다른 시스템 레이어(프레, 서비스, 데이터) 간의 객체 전달 등... 
정리. DTO란 애플리케이션의 상이한 계층끼리 주고받을 수 있는 구조화되고 표준화된 데이터 집합이다. 캡슐화, 관심의 분리, 버전 관리와 상호 호환성등에서 얻을 수 있는 이점이 많다.<br>
  
## 
