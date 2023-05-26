package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void StatefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        int userBPrice = statefulService1.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
        System.out.println("userAPrice = " + userAPrice);

        // 공유 필드 조심, 스프링 빈은 항상 무상태(statless)로 설계
        // Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
     
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
    
}
