package org.example.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.orderservice.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponseDto {


    private Long id;
    private List<ItemDto> items;
    private String userName;
    private OrderStatus orderStatus;
    private Double total;
    private Date orderDate;
    private List<AddressResponseDto> address;

   public static OrderResponseDto fromOrder(Order order){
        OrderResponseDto orderResponseDto=new OrderResponseDto();

        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setItems(new ArrayList<>());
        for (Item item:order.getItems()) {
            orderResponseDto.getItems().add(ItemDto.fromItem(item));
        }
        orderResponseDto.setTotal(order.getTotal());
        orderResponseDto.setAddress(new ArrayList<>());
        for (AddressEntity addressEntity:order.getAddress()) {
            orderResponseDto.getAddress().add(AddressResponseDto.fromAddress(addressEntity));
        }
        orderResponseDto.setUserName(order.getUser().getUsername());
        return orderResponseDto;
    }
}
