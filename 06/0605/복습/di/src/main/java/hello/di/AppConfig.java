package hello.di;

import hello.di.Order.OrderService;
import hello.di.Order.OrderServiceImpl;
import hello.di.discount.DiscountPolicy;
import hello.di.discount.FixDiscountPolicy;
import hello.di.discount.RateDiscountPolicy;
import hello.di.member.MemberRepository;
import hello.di.member.MemberService;
import hello.di.member.MemberServiceImpl;
import hello.di.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                getMemberRepository(),
                getDiscountPolicy());
    }

    @Bean
    private static DiscountPolicy getDiscountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }
}
