package hello.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    private Member member;

}
