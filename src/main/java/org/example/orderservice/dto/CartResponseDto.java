package org.example.orderservice.dto;


import lombok.Getter;
import lombok.Setter;
import org.example.orderservice.model.Cart;
import org.example.orderservice.model.Item;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartResponseDto {

    private Long cartId;

    private List<ItemDto> itemsList;

    private Double grandTotal;


    public static CartResponseDto fromCart(Cart cart){
        CartResponseDto cartResponseDto=new CartResponseDto();
        cartResponseDto.setCartId(cart.getId());
        cartResponseDto.setGrandTotal(cart.getGrandTotal());
        List<ItemDto> itemList1=new ArrayList<>();
        for(Item i: cart.getItems()){
            ItemDto item=new ItemDto();
            item.setId(i.getId());
            item.setSubTotal(i.getSubTotal());
            item.setQuantity(i.getQuantity());
            item.setProduct(i.getProduct());
            itemList1.add(item);
        }
        cartResponseDto.setItemsList(itemList1);
        return cartResponseDto;
    }
}
