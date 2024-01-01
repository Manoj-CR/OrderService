package org.example.orderservice.controller;

import org.example.orderservice.kafka.dto.PaymentEvent;
import org.example.orderservice.dto.OrderRequestDto;
import org.example.orderservice.dto.OrderResponseDto;
import org.example.orderservice.kafka.OrderProducer;
import org.example.orderservice.model.Order;
import org.example.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    private OrderProducer orderProducer;


    public OrderController(OrderService orderService, OrderProducer orderProducer) {
        this.orderService = orderService;
        this.orderProducer = orderProducer;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long order_id) {
        try {
            Order order = orderService.getOrderById(order_id);
            return new ResponseEntity<>(OrderResponseDto.fromOrder(order), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Order order = orderService.createOrder(orderRequestDto.getCartId(),orderRequestDto.getAddressId(),username);
            OrderResponseDto orderResponseDto = OrderResponseDto.fromOrder(order);
            System.out.println(orderResponseDto.toString());
            return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody Order order){
        PaymentEvent paymentEvent = new PaymentEvent();
        paymentEvent.setAmount(order.getTotal());
        paymentEvent.setEmail(order.getUser().getUsername());
       paymentEvent.setOrderId(order.getId());
        paymentEvent.setStatus("Pending");
        paymentEvent.setMessage("Order waiting for payment confirmation");
        orderProducer.sendMessage(paymentEvent);
        return "Order placed successfully";
    }




}
