package org.example.orderservice.model;





import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;


import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;

    @ManyToOne (cascade = CascadeType.ALL)
    private Product product;

    @Column(name="quantity")
    private int quantity;

    @Column(name="sub_total")
    private Double subTotal;

    @ManyToMany (mappedBy = "items")
    @JsonIgnore
    private List<Order> orderList;

    @ManyToMany (mappedBy = "items")
    @JsonIgnore
    private List<Cart> cartList;

}
