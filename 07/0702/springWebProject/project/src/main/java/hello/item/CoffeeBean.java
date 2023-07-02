package hello.item;

import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
public class CoffeeBean extends Item{

    @Enumerated(EnumType.STRING)
    private CoffeeVariety variety;

    @Enumerated(EnumType.STRING)
    private CoffeeOrigin origin;

    @Embedded
    private CoffeeTaste taste;


}
