package org.example.orderservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.orderservice.dto.CartRequestDto;
import org.example.orderservice.dto.CartResponseDto;
import org.example.orderservice.model.Cart;
import org.example.orderservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/cart")
public class CartController {


    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/")
    public ResponseEntity<CartResponseDto> addToCart(HttpServletRequest request, @RequestBody CartRequestDto cartDto){
        try {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userId = authentication.getName();
            Cart cart=cartService.addToCart(userId,cartDto.getCartId(), cartDto.getQuantity(), cartDto.getProductId());
            return new ResponseEntity<>(CartResponseDto.fromCart(cart),header,HttpStatus.OK);
        }catch (Exception e){
                ResponseEntity<List<CartResponseDto>> responseEntity = new ResponseEntity<>
                        (HttpStatus.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 error code
            }

    }
    @DeleteMapping(value ="/",params = {"productId", "quantity","cartId"})
    public ResponseEntity<CartResponseDto> deleteFromCart( @RequestParam("productId") Long productId,
                                                           @RequestParam("quantity") Integer quantity,
                                                           @RequestParam("cartId") Long cartId ){
        try {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");

            Cart cart=cartService.deleteFromCart(cartId,quantity,productId);
            return new ResponseEntity<>(CartResponseDto.fromCart(cart),header,HttpStatus.OK);
        }catch (Exception e){
            ResponseEntity<List<CartResponseDto>> responseEntity = new ResponseEntity<>
                    (HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 error code
        }
    }

    @PostMapping(value ="/",params = {"cartId"})
    public ResponseEntity<CartResponseDto> deletecart(@RequestParam("cartId") Long cartId ){
        try {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");

            Cart cart=cartService.deletecart(cartId);
            return new ResponseEntity<>(CartResponseDto.fromCart(cart),header,HttpStatus.OK);
        }catch (Exception e){
            ResponseEntity<List<CartResponseDto>> responseEntity = new ResponseEntity<>
                    (HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 error code
        }
    }




}
