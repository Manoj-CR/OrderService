package org.example.orderservice.service;

import org.example.orderservice.dto.AddressRequestDto;
import org.example.orderservice.model.AddressEntity;
import org.example.orderservice.model.User;
import org.example.orderservice.repository.AddressRepository;
import org.example.orderservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private AddressRepository addressEntityRepository;

    private UserRepository userRepository;

    public AddressService(AddressRepository addressEntityRepository, UserRepository userRepository) {
        this.addressEntityRepository = addressEntityRepository;
        this.userRepository = userRepository;
    }

    public List<AddressEntity> getAllAddressForAUser(String userName) {
        User user=userRepository.findUserByUsername(userName);
        Optional<List<AddressEntity>> addressEntities=addressEntityRepository.
                findAddressEntitiesByUserId(user.getId());
        return addressEntities.orElse(null);
    }

    public AddressEntity getSingleAddress(Long id) {
        Optional<AddressEntity> addressEntities=addressEntityRepository.
                findAddressEntitiesById(id);
        return addressEntities.orElse(null);
    }

    public AddressEntity addAddress(AddressRequestDto address,String userName){
        User user=userRepository.findUserByUsername(userName);

        AddressEntity addressEntity=AddressRequestDto.fromAddressRequestDto(address);
        addressEntity.setUser(user);
        return addressEntityRepository.save(addressEntity);
    }
    public void deleteAddress(Long id) {
         addressEntityRepository.deleteById(id);
    }
}
