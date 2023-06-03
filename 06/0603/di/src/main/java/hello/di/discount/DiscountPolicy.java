package hello.di.discount;

import hello.di.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
