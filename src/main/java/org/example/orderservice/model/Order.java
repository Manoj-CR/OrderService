package org.example.orderservice.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;


import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable (name = "Order_Items" , joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn (name = "item_id"))
    @JsonIgnore
    private List<Item> items;

    @ManyToOne (fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name="total")
    private Double total;

    @Column(name="orderDate")
    private Date orderDate;

    @OneToMany (fetch = FetchType.EAGER,mappedBy = "order", cascade = CascadeType.MERGE)
    private List<AddressEntity> address;
}
