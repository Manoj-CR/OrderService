package org.example.orderservice.controller;

import org.example.orderservice.dto.AddressRequestDto;
import org.example.orderservice.model.AddressEntity;
import org.example.orderservice.service.AddressService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;


    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/")
    public List<AddressEntity> getAllAddressForAUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return addressService.getAllAddressForAUser(username);
    }

    @GetMapping("/{addressId}")
    public AddressEntity getSingleAddress(Long id) {
        return addressService.getSingleAddress(id);
    }

    @PostMapping("/")
    public AddressEntity addAddress(@RequestBody AddressRequestDto addressRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return addressService.addAddress(addressRequestDto,username);
    }
    public void deleteAddress(Long id) {
        addressService.deleteAddress(id);
    }


}
