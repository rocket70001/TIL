package hello.domain;

import hello.item.Item;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderItem {

    private List<Item> items = new ArrayList<>();
}
