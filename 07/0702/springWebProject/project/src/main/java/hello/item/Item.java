package hello.item;

import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Setter
public class Item {

    @Id @GeneratedValue
    private Long itemId;

    private String name;

    private int price;

    private int stockQuantity;
}
