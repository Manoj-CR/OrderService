package org.example.orderservice.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.orderservice.model.Item;
import org.example.orderservice.model.Product;
@Getter
@Setter
public class ItemDto {

    private Long id;
    private Product product;
    private int quantity;
    private Double subTotal;

    public static ItemDto fromItem(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setProduct(item.getProduct());
        itemDto.setQuantity(item.getQuantity());
        itemDto.setSubTotal(item.getSubTotal());
        return itemDto;
    }
}
