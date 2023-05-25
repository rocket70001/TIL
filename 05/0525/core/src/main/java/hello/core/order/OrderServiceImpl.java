package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // DI 위반 사례 -> 인터페이스 DiscountPolicy 뿐만 아니라 FixDiscountPolicy 구현체에도 의존
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // OCP 위반 -> 위의 DI 위반 때문에 클라이언트인 OrderServiceImpl도 변경해야 한다.
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy; // 위의 문제로 인터페이스에만 의존하도록 코드 변경(DIP 준수)
    private final MemberRepository memberRepository;

    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    
}
