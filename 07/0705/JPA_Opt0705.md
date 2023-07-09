# 스프링 부트와 JPA 활용2 - API 개발과 성능 최적화(0705)

## @RequestBody
Json, XML 등으로 전달된 데이터를 매개변수 값으로 넣어준다. 
(@RequestBody @Valid)와 @NotEmpty(엔티티 변수에)와 같이 사용해 값을 강제할 수도 있다. 
하지만 api 스펙을 위해 엔티티 변수를 직접 건드리는 방식은 쓰면 안 된다. 
해당 엔티티를 참조하는 여러 계층이 있을 수 있기 때문이다. 따라서 이와 같은 방식으로 사용하려면 엔티티를 건드려도 되는
 별도의 DTO를 만들어서 사용해야 한다. API를 만들때는 엔티티를 파라미터로 받으면 안 된다. 엔티티와 파라미터를 매핑해주는 내부 클래스를 만들어 활용하자. 
정리. API를 만들 때는 엔티티를 외부에 노출하지 않고 값에 조건을 둘 수 있는 별도의 DTO를 만들어 활용하자.<br>
API는 절대로 엔티티를 그대로 사용하지 않는다.<br>
엔티티에는 프레젠테이션을 위한 로직이 있으면 안된다.<br>
  
## 커맨드와 쿼리는 철저하게 분리하자.
커맨드 안에 쿼리를 같이 두면 유지 보수에 불리하다.<br>
  
## 엔티티에서 특정 데이터만을 추출하고 싶을 때
엔티티의 스펙을 @NotEmpty나 @NoJson 등으로 변경하지 않고 필요한 데이터만 노출해야 한다. 클라이언트가 자신의 주문 
정보를 원하는데 모든 회원 정보를 노출할 필요는 없기 때문이다. 엔티티 단에서 제약을 걸어버리면 클라이언트가 
원하는 정보를 원할 때 여러 제약 사항이 복잡하게 얽혀버린다. 이때는 스트림과 제네릭 클래스를 활용해 특정 정보만을 
추출해 배열<제네릭 타입>으로 추출해낼 수 있다. 별도의 DTO 클래스와 제네릭 클래스를 만들어 DTO 클래스를 감싼 
제네릭 클래스를 반환하면 해결할 수 있다.<br>
  
## API 조회 성능향상
CRUD 관련 장애 중 90%는 조회 기능에 의해 발생한다. 

