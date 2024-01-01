package org.example.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CartRequestDto {

    private Long productId;
    private int quantity;
    private Long cartId;
}
