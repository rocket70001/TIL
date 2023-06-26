# Spring 정리 - 0626(JPA 구동 원리와 매핑)

## JPA의 작동원리
resources의 META-INF에 있는 persistence.xml파일에서 JPA의 설정정보를 조회한 뒤 Persistence 클래스에서 createEntityMangerFactory()를 통해 Entity() 객체를 생성한다. @Entity가 붙은 클래스를 두면 클래스 내부의 변수들을 자동으로 매핑해 DB에 객체를 생성할 수 있다. 이때 PK를 의미하는 @Id애노테이션이 붙은 변수는 필수로 작성한다.

## NoSuchMethodError 발생
start.spring.io에서 maven 빌드 환경, 스프링 부트 2.7, 자바 11,  H2 2.1.214로 실행 -> NoSuchMethodError 발생  
동일 환경에서 스프링 부트 3.0 이상으로 실행 -> NoSuchMethodError 발생
스프링 부트 2.~로 복귀, ~/test.mb.db 파일 삭제, 최신 버전 H2 삭제 후 H2 1.4.200 다운받아 실행 -> NoSuchMethodError 발생  
hibernate-commons-annotations(5.0.4)와 hibernate-core(5.6.15)의 버전이 달라 pom.xml 파일 의존성 갱신  
hibernate-core의 버전이 5.6.15로, hibernate-commons-annotations이 5.1.2 Final로 바뀜.  
이후 메인 메서드 실행시 NoSuchMethodError는 사라졌지만 PersistenceException 발생  
Member클래스 @Id에 @GeneratedValue를 붙였던 게 원인 -> @GeneratedValue 제거 후 정상 실행 

## 자바 객체의 값을 바꿔서 DB값 변경하기
트랜잭션이 진행 중인 객체는 JPA의 관리 대상이 된다. 그 상태에서 JPA의 관리 대상이 된 객체의 값을  
세터 등을 이용해 변경하게 되면 트랜잭션 커밋 시점에 JPA가 변경사항을 파악해 update 쿼리를 날리게 된다.  
이런 방식으로 자바 객체의 값을 변경하는 것 만으로 DB의 실제 데이터 값을 변경할 수 있다.

## 엔티티 매니저 사용시 주의사항
1. 모든 데이터 변경은 트랜잭션 안에서만 진행한다.  
2. 엔티티 매니저 팩토리를 통해 생성한 엔티티 매니저는 한 번 사용 후 바로 버려야 한다.  
3. 엔티티 매니저 팩토리 자체는 하나만 생성해 애플리케이션 전체에서 공유한다.  

## JPQL
JPQL은 테이블 중심이 아닌 객체를 중심으로 DB를 조회하기 위해 고안된 JPA의 문법이다.  
SQL과 비슷하지만 앞서 말한 것처럼 테이블을 조인해 값을 찾는 게 아니라 객체를 앨리어스로 치환하고  
메서드 체이닝을 통한 조건을 걸어 조회한다. SQL을 추상화한 객체 지향 쿼리 언어이다.  

## JPA의 영속성 관리
JPA의 핵심은 ORM(Object Relational Mapping -> 데이터 종속적인 개발 지양, 데이터를 어떻게 객체지향적으로 매핑할 것인가?)과 영속성 컨텍스트이다.

## 엔티티 매니저 팩토리와 엔티티 매니저
엔티티 매니저 팩토리는 고객의 요청이 올 때마다 엔티티 매니저를 생성해 고객에게 제공한다.  
엔티티 매니저는 데이터 풀에서 커넥션을 가져와 DB에 접근할 수 있는 권한을 가진다.  
엔티티 매니저는 일회용으로 사용 후 폐기한다.  

## 영속성 컨텍스트
"엔티티를 영구 저장하는 환경"이라는 뜻이다. 
```java
EntityManager.persist(entity);  
```
#### 엔티티는 비영속(new/transient) -> 영속(managed) -> 준영속(detached) -> 삭제(removed)의 생명주기를 가진다.  
---
- 비영속 상태는 객체를 생성한 상태이다. 객체를 생성했지만 아직 JPA의 관리 대상은 아닌 상태를 말한다.  
---
- 영속 상태는 em.persist(entity)와 같이 EntityManager의 persist를 통해 JPA의 관리 대상이 된 상태이다. persistence에 state된 상태이다.(1차 캐시, 동일성 보장(Identity), 트랜잭션을 지원하는 쓰기 지연(Transaction Write-Behind), 변경 감지(Dirty Checking), 지연 로딩(Lazy Loading)) hibernate batch size 옵션     

- 영속 상태 동작 원리  
-> 영속성 컨텍스트가 begin();되면 객체의 최초 상태를 스냅샷으로 남겨놓는다. 이후 persist(); find(); 등 엔티티 매니저에 의해 실행되는 메서드들은 1차 캐시에 수행된 값을 state하고 쿼리는 Transaction Write-Behind에 의해 쓰기 지연 저장소에 state된다. 최종적으로 트랜잭션이 커밋되면 내부적으로 flush()가 호출되고 쓰기 지연 저장소에 있던 SQL UPDATE 쿼리가 DB에 전달된다. 예외 상황을 제외하면 영속성 컨텍스트의 쿼리는 단 한 번만 DB에 전송되고 그 사이 진행되는 모든 연산은 1차 캐시, 쓰기 지연 저장소에서 조회/수정/삭제된다.
  
- flush()  
-> flush()는 영속성 컨텍스트에 있는 쿼리를 DB에 전송하는 메서드이다. 트랜잭션 커밋시, JPQL 쿼리 실행시 flush()가 자동 호출된다. JPQL은 SQL을 추상화해 매핑하는 쿼리이므로 자동 커밋한다. em.flush()와 같은 형태로 엔티티 매니저를 통해 직접 호출할 수도 있다. 주의할 점, flush()가 영속성 컨텍스트 자체를 비우지는 않는다. 데이터베이스에 동기화할 뿐이다. 
---
- 준영속 상태는 영속 상태의 엔티티가 영속성 컨텍스트에서 분리되는 상태이다. 따라서 준영속 상태에서는 영속성 컨텍스트가 제공하는 기능을 사용할 수 없다.  
  
- 복잡한 상황에서 특정 엔티티의 값만 변경한 뒤 해당 엔티티를 컨텍스트에서 제외하고 싶을 때 detach()를 사용해 준영속 상태로 해당 엔티티를 분리할 수 있다. clear()는 전체 컨텍스트를 초기화한다. close()는 영속성 컨텍스트를 종료한다. 이 세 가지 방법으로 영속성 컨텍스트를 준영속 상태로 만들 수 있다. 복잡한 상황이나 응용에 사용되므로 실제 사용되는 일이 많지는 않다.  
---

## 객체와 테이블 매핑
@Entity가 붙은 클래스는 JPA가 관리하는 클래스이다. 따라서 JPA를 사용해 테이블과 매핑할 클래스는 반드시 @Entity를 달아야 한다.  
기본생성자(파라미터 x) 필수, (final,enum, iterface, inner 클래스 사용 x)  
@Table은 엔티티와 매핑할 테이블을 지정한다.

## 데이터베이스 스키마 자동 생성
DB 방언을 사용해 DB에 맞는 DDL을 애플리케이션 실행 로딩 시점에 생성해준다. 운영에서는 쓰지 않고 개발 단계에서 사용한다.  
persistence.xml의 hibernate.hbm2ddl.auto의 밸류를 create(DROP + CREATE), create-drop(create와 같으나 종료 시점에 DROP), update(변경분만 DB 반영), validate(엔티티와 테이블이 정상 매핑 되었는지만 확인), none(아무것도 안함) 등으로 설정해 반영할 수 있다. 
개발 초기(create or update), 테스트 서버(update or validate), 스테이징 (validate or none), 운영단계에서는 절대로 이 기능을 사용하지 않아야 한다.  
애플리케이션 실행 시점에 시스템이 자동적으로 alter를 사용하는 게 위험하다. 잘못하면 서비스가 중지될 수도 있다.

## @Column
(unique = true, length = 10)등의 옵션을 걸 수 있다. 테이블과 마찬가지로 따로 이름 설정을 할 수도 있지만 중복되거나 특이한 경우가 아닌 이상 기본값을 사용한다. unique 제약 조건은 @Table의 옵션인 uniqueConstraints와 같지만 이름이 알아보기 힘들게 랜덤 값으로 들어가서 거의 쓰지 않는다. unique 제약조건을 사용하려면 이름을 지정할 수 있는 @Table에서 사용하는 게 좋다.  
  
## @Enumerated
자바 enum을 타입으로 사용할 수 있음, EnumType.ORDINAL, EnumType.STRING 옵션을 걸어 순서를 DB에 저장하거나 ENUM이름을 DB에 저장할 수 있다. ORDINAL은 디폴트 값이지만 쓰면 안 된다고 생각하자. enum 클래스 안에서 enum값의 순서가 바뀌면 DB의 값에 혼란이 생길 수 있다. 반드시 EnumType.STRING을 사용하자.
  
## @Temporal
자바 Date 타입과 DB Date 타입이 다른 경우가 있어 맞춰준다. 자바8에 도입된 LocalDate, LocalDateTime를 하이버네이트가 자동으로 매핑해주는 덕분에 요즘에는 쓰지 않는다.    
  
## @Lob
Large Object의 줄임말이다. 크기가 큰 바이너리 데이터나 이미지, 영상, 텍스트 등을 매핑할 때 쓴다.  
  
## @Transient
메모리에만 임시 저장해 사용하고 DB에 매핑하지는 않을 변수에 쓴다.  
  

