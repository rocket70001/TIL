package hello.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MemberTaste {

    @Id
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // 맛에 대한 취향은 0~100까지 숫자로 표현한다.
    private int sweet;

    private int bitter;

    private int acidity;

    private int aroma;

    private int balance;

    private int body;

    // SpecialAroma의 값 중 가장 선호하는 향을 선택한다.
    @Enumerated(EnumType.STRING)
    private SpecialAroma specialAroma;
}
