package org.example.orderservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Entity
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name",unique=true)
    private String username;

    @OneToMany (fetch = FetchType.LAZY,mappedBy = "user")
    private List<AddressEntity> addressEntity;

    @OneToMany (fetch = FetchType.EAGER,mappedBy = "user")
    @JsonIgnore
    private List<Order> orders;

    @OneToOne (fetch = FetchType.EAGER)
    @JsonIgnore
    private Cart cart;

}
