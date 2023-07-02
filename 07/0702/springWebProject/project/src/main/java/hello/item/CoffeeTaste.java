package hello.item;

import hello.domain.SpecialAroma;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class CoffeeTaste {

    // 맛에 대한 취향은 0~100까지 숫자로 표현한다.
    private int sweet;

    private int bitter;

    private int acidity;

    private int aroma;

    private int balance;

    private int body;

    @Enumerated(EnumType.STRING)
    private SpecialAroma specialAroma;
}
