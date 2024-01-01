package org.example.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class OrderRequestDto {

    private Long cartId;
    private Long addressId;
}
