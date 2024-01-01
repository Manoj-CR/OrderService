package org.example.orderservice.service;

import org.example.orderservice.model.*;
import org.example.orderservice.repository.OrderRepository;
import org.example.orderservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private CartService cartService;
    private final OrderRepository orderRepository;

    private final AddressService addressService;
    private UserRepository userRepository;

    public OrderService(CartService cartRepository, OrderRepository orderRepository, AddressService addressService, UserRepository userRepository) {
        this.cartService = cartRepository;
        this.orderRepository = orderRepository;
        this.addressService = addressService;
        this.userRepository = userRepository;
    }

    public Order createOrder(Long cartId, Long addressId, String username) {
        Order order = new Order();
        Cart cart= cartService.getCartById(cartId);
        order.setOrderDate(new Date());
        order.setTotal(cart.getGrandTotal());
        List<Item> itemList=new ArrayList<>();
        for(Item item:cart.getItems()){
            itemList.add(item);
        }
        order.setItems(itemList);
        User user=userRepository.findUserByUsername(username);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.CREATED);
        AddressEntity address=addressService.getSingleAddress(addressId);
        List<AddressEntity> addressEntities=new ArrayList<>();
        addressEntities.add(address);
        order.setAddress(addressEntities);
        order=orderRepository.save(order);
        cartService.deletecart(cart.getId());
        return order;
    }

    public Order getOrderById(Long orderId) {
        Optional<Order> orderOptional= orderRepository.findById(orderId);
        return orderOptional.orElse(null);
    }
}
