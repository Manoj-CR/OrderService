package org.example.orderservice.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEvent {

    private String message;
    private String status;
    private String email;
    private String phoneNumber;
    private Long orderId;
    private Double amount;
}
