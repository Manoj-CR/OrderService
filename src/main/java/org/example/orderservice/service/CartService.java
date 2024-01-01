package org.example.orderservice.service;

import org.example.orderservice.client.ProductClient;
import org.example.orderservice.model.Cart;
import org.example.orderservice.model.Item;
import org.example.orderservice.model.Product;

import org.example.orderservice.model.User;
import org.example.orderservice.repository.CartRepository;
import org.example.orderservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CartService {


    private  ProductClient productClient;
    private  CartRepository cartRepository;

    private UserRepository userRepository;


    public CartService(ProductClient productClient, CartRepository cartRepository, UserRepository userRepository ) {
        this.productClient = productClient;
        this.cartRepository = cartRepository;
        this.userRepository=userRepository;
    }

    public Cart addToCart(String username,Long cartId, int quantity, Long productId) {


        Cart cart= cartRepository.getCartById(cartId);
        if(cartId==null){
            User user1=new User();
            user1.setUsername(username);
            user1.setCart(new Cart());
            cartRepository.save(user1.getCart());
            user1=userRepository.save(user1);
            cart=user1.getCart();
        }

        Product product=productClient.getProductById(productId);

        List<Item> itemList=cart.getItems();
        if(itemList==null){
            itemList=new ArrayList<>();
        }
        Double grandTotal=cart.getGrandTotal();
        if(grandTotal==null){
            grandTotal=0.0;
        }
           if(checkIfItemExist(itemList,productId)){
               itemList=changeExistingQuantity(itemList,productId,quantity);
           }else{
            Item item = new Item();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setSubTotal(product.getPrice() * quantity);
            itemList.add(item);
        }
        grandTotal=0.0;
        for(Item i:itemList){
            grandTotal+=i.getSubTotal();
        }
        cart.setItems(itemList);
        cart.setGrandTotal(grandTotal);
        cart = cartRepository.save(cart);
        return cart;
    }

    private List<Item> changeExistingQuantity(List<Item> itemList, Long productId, int quantity) {
        List<Item> result=new ArrayList<>();
        for (Item i : itemList) {
            Item item=new Item();
            item.setId(i.getId());
            item.setQuantity(i.getQuantity());
            item.setProduct(i.getProduct());
            item.setSubTotal(i.getSubTotal());
            if (Objects.equals(i.getProduct().getId(), productId)) {
                item.setQuantity(i.getQuantity()+quantity);
                item.setSubTotal(item.getQuantity()*i.getProduct().getPrice());
            }
            result.add(item);
        }
        return result;
    }

    public Boolean checkIfItemExist(List<Item> itemList , Long productId) {
        for (Item i : itemList) {
            if (Objects.equals(i.getProduct().getId(), productId)) {
                return true;
            }
        }
        return false;
    }

    public Cart deleteFromCart(Long cartId, int quantity, Long productId) {

        Cart cart= cartRepository.getCartById(cartId);
        List<Item> itemList=cart.getItems();
        List<Item> result=new ArrayList<>();
        for (Item i : itemList) {
            if (!Objects.equals(i.getProduct().getId(), productId)) {
                result.add(i);
            }
        }
        Double grandTotal=0.0;
        for(Item i:result){
            grandTotal+=i.getSubTotal();
        }
        cart.setItems(result);
        cart.setGrandTotal(grandTotal);
        cart = cartRepository.save(cart);

        return cart;
    }

    public Cart deletecart(Long cartId) {
        Cart cart= cartRepository.getCartById(cartId);
        cart.setGrandTotal(0.0);
        cart.setItems(new ArrayList<>());
        cartRepository.save(cart);
        return cart;
    }

    public Cart getCartById(Long cartId) {
        return cartRepository.getCartById(cartId);
    }
}
