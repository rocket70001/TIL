# Spring정리 0628(JPA 값 타입, JPQL)  

## 값 타입 컬렉션  
@ElementCollection, @CollectionTable(name = "", joinColumns = @JoinColumn(name = ""))   
값 타입을 하나 이상 저장시 사용한다. DB에 칼럼에 컬렉션을 저장할 수 없으니 별도의 테이블을 만들어 저장한다.  
값 타입은 영속성 전이와 고아 객체 제거 기능을 필수로 갖는다.  
  
List\<String\>도 오브젝트 타입이기 때문에 세터로 변경하는 게 아니라 제거하고 새 값을 넣는 식으로 값을 변경한다.  
  
임베더블, 임베디드 애노테이션을 사용해 값 타입의 변수들을 특정 클래스에 두고 타 테이블에서 해당 클래스 타입의 변수를 만들어  
실제 DB반영시 반영할 수 있다.

```java
@Entity
public class Member {
    @Id
    private Long id;

    // Other member attributes
    
    @Embedded
    private Address address;
    
    // Getters and setters
}

@Embeddable
public class Address {
    private String person;
    private String address;
    private String street;
    
    // Constructors, getters, and setters
}
```

위 엔티티 객체 생성시 DB에는 id, person, address, street 칼럼이 담긴 한 테이블이 만들어진다.  
  
## JPQL
테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리이다.  
동적 쿼리 생성을 위한 Criteria 기능이 있긴 하지만 더 나은 QueryDSL을 쓰자.  
특정DB에 종속적이지 않도록 SQL을 추상화해 사용한다.  

## TypedQuery, Query  
반환타입이 명확하지 않을 때 Query를 사용한다.
  
## 프로젝션
select 절에 조회할 대상을 지정하는 것이다. 엔티티, 임베디드 타입, 스칼라 타입(숫자, 문자 등 기본 데이터 타입) 등 형식에 따라 조금 달라진다.  
  
임베디드 타입은 임베더블 객체의 앨리어스로 프로젝션 해야된다. 임베디드 객체만으로는 안 됨(DB에 없으니까)  
  
스칼라 타입은 일반 SQL 프로젝션을 생각하면 된다.  
  
여러 값을 조회할 때는 Query 타입, Object[] 타입, new 명령어로 조회할 수 있다.  
  
new 명령어로 국소적인 생성자를 만들어 객체의 일부분만 호출할 수 있다.  
  
## 조인
내부, 외부, 세타(카테시안 곱) 조인이 있다.  
FROM절 서브쿼리는 JPQL에서 불가능하므로 조인으로 풀어서 써야 한다. JPA는 WHERE, HAVING 절에서만 서브 쿼리를 사용할 수 있다.  

## JPQL 함수
SQL과 대부분 비슷하다. 그 밖에 사용자 정의 함수가 있는데 내가 사용하는 Dialect를 상속받아 해당 클래스를 참조해 만들 수 있다.  
  
## JPQL 경로 표현식
m.username과 같이 .을 통해 객체 그래프를 탐색하는 것이다.  
상태 필드, 단일 값 연관 필드, 컬렉션 값 연관 필드에 따라 내부 동작이 달라진다. 세 가지를 반드시 구분해야 한다.   
상태 필드는 경로 탐색의 끝으로 더이상 탐색이 불가능하다. 단일 값 연관 경로는 묵시적 내부 조인이 발생해 추가적인 탐색이 가능하다.  
컬렉션 값 연관 경로도 묵시적 조인이 발생하지만 탐색이 불가능하다.  
실무에서는 묵시적 내부 조인을 주의해서 사용해야 하거나 되도록 사용하지 않아야 한다.(side effect)  
FROM절에서 명시적 조인을 통해 별칭을 얻으면 별칭을 통해 탐색 가능하므로 명시적 조인만 사용하자.  

## 중요!!! Fetch 조인

