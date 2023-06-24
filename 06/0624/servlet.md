# 스프링 복습(06/24)

## Servlet

서블릿은 자바에서 서버사이드의 HTTP 요청 처리/응답을 위해 고안된 서버사이드의 API이다.  

### 서블릿의 생명주기
init(), service(), destroy()
  
브라우저에서 HTTP 메시지를 송신하면 WAS측에서 HTTP의 메서드, 매개변수, URL, 헤더, 바디 등을 파싱해  
객체화한다. 이 객체는 문자열 파싱 뿐만 아니라 파싱된 정보를 임시로 저장하고 조회 가능하게끔 하며  
세션 관리 기능도 제공한다. 대표적인 서블릿 객체로는 HttpServeltRequest/HttpServelResponse 등이 있다.
이렇게 만들어진 서블릿 객체를 데이터베이스와 연동하거나 애플리케이션 로직을 처리하게 된다.  
  
정리. 서블릿은 브라우저에서 WAS로 전달된 HTTP를 파싱하고 객체화하여 서블릿 컨테이너(스프링부트)에 전달한다.  
서블릿 객체의 자체 기능으로는 데이터의 임시저장/조회/세션관리 등이 있다. 서블릿 객체를 이용하면 고객의 요청에 따라  
동적으로 애플리케이션 로직을 수행하고 DB를 갱신할 수 있다.  

### @WebServlet
해당 에노테이션은 Deployment Disciptor(배포 설명자, web.xml과 같은 애플리케이션 설정 파일) 없이도  
해당 클래스가 서블릿 클래스임을 알려주는 에노테이션이다. 따라서 @WebServlet만으로도 서블릿의 기능인 URL 매핑과 파라미터 초기화 등의  
기능을 적용할 수 있다. name과 urlPatterns를 파라미터로 적용할 수 있는데 name은 옵션 사항이다.  

### HTTP 요청사항
일반적으로 3가지 메서드를 사용한다.
1. GET - URL에 쿼리 파라미터로 전달 \/url?name=john&age=20(검색, 필터, 페이징 등에 사용)
2. POST - 메시지 바디에 쿼리 파라미터로 전달 content-type: application/x-www-form-urlencoded, username=hello&age=20(회원가입, 상품주문, HTML FORM 등에 사용)
3. POST, PUT, PATCH - HTTP message body에 직접 담아 전달, HTTP API에서 주로 사용한다.(JSON, XML, TEXT)

### Response
HttpServelResponse에 있는 getWriter() 메서드를 통해 PrintWriter 객체를 생성할 수 있다. writer이라는 객체를 생성했다면 다시 PrintWriter 클래스에 있는 메서드인 write()메서드를 통해 XML을 작성하면 해당 HTML이 브라우저를 통해 렌더링된다. Servlet은 자바 로직을 활용한 값을 HTML에 넣을 수 있어 동적 렌더링을 가능하게 했다.  
하지만 자바 환경에서 XML문자열을 +연산자로 일일이 이어붙여야 한다는 한계를 가졌다.  
이런 문제를 해결하기 위해 JSP, Thymeleaf 등의 템플릿 엔진이 등장했다.  

### 템플릿 엔진
템플릿 엔진은 HTML 작성등의 편의를 위해 등장했다. 비즈니스 로직과 보여주기 위한 로직을 구분해주고(관심사의 분리)  
재사용성을 높이고 HTML을 동적으로 편집해준다.  

### JSP(JavaServer Page)
자바 엔터프라이즈 버전에서 서버사이드를 위한 기술로 등장했으나 비즈니스 로직과 HTML을 같이 둬야 한다는 단점이 있다.  
이 단점때문에 직접 사용하는 경우는 드물고 Spring MVC에 의해 추상화되어 사용된다.  

### 서버사이드 기술의 발전

1. Servlet - Http메시지의 객체화, 동적 HTML 작성을 가능케 함 -> 문제. 복잡하고 번거로움
2. JSP - 자바 코드와 HTML을 얼마간 분리해 보다 편리한 HTML 작성을 가능하게 함 -> 문제. 관심사의 분리가 안 됨  
3. Spring MVC + Template Engine(Thymeleaf) - 모델, 뷰, 컨트롤러, 서비스로 관심사를 분리.

### Spring MVC
프로세스 수행과 제어(컨트롤러) -> 수행 결과 집합(모델) -> 렌더링(뷰)  
별도로 서비스, 리포지토리를 두기도 하는데 서비스는 컨트롤러에서 비즈니스 로직을 분리한다.  

### @Repository(@Component를 포함한다.)
DB접근을 위한 스프링의 persistance operation 애노테이션이다.  
DAO(Data Access Object)를 생성해 DB에 접근해 CRUD를 실행하는 클래스가 된다.  
주요 장점으로 DataAccessException을 자동으로 핸들링하는 메커니즘을 제공한다.  
@Repository 클래스의 객체는 @Autowired를 통한 의존성 주입이 가능하다.(스프링 컨테이너에 등록된 빈)  
당연히 생성자 주입도 가능하다.  
@Transactional 애노테이션을 통해 트랜잭션도 지원하며 이때 트랜잭션 Consistency와 Integrity를  
보장해준다.
@PersistenceContext는 spring과 JPA 사이를 seamless하게 연결한다고 표현된다.  
seamless한 연결이란 Spring이 추가적인 지원을 통해 JPA의 기능을 단순, 추상화해 제공한다는 의미로  
통용된다. @Autowired를 통해 JPA의 컴포넌트를 주입할 수 있는 것도 이러한 Spring의 JPA 지원 덕분에 가능하다.  
JPA 구성요소를 스프링이 관리하는 빈에 등록해 애노테이션 프로세싱을 통해 접근할 수 있게 함으로서 이러한 추상화가 가능하다.  
다시 @PersistenceContext로 돌아가보면 @PersistenceContext는 일반적으로 JPA의 EntityManager 인터페이스를 구현해 데이터 persist, 복구, 업데이트, 삭제를 편리하게 한다.   
persist는 커밋을 하기 전까지 DB에 insert 쿼리를 보내지 않는다.

### @Service
MVC 중 비즈니스 로직을 담당하는 서비스 클래스임을 알리는 애노테이션이다.  
서비스 클래스는 비즈니스 로직, 그에 대한 검증, 계산 등을 캡슐화한다.  
비즈니스 로직이 트랜잭션 단위로 사용될 수도 있기 때문에 @Transactional 애노테이션도 지원한다.  
읽기 전용 메서드인 경우 @Transactional(readOnly = true)를 통해 데이터베이스에서 리소스 활용을 최소화할 수 있다.  
컨트롤러(Controller)와 데이터 접근계층(Repository) 사이에서 추상 계층의 역할을 한다.  
컨트롤러와 리포지토리 사이에서 데이터의 변환, 병합 등에 관여할 수 있다.  
스프링 컴포넌트이기 때문에 당연히 @Autowired나 생성자를 통한 의존성 주입이 가능하다. 

