package org.example.orderservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.orderservice.model.AddressEntity;

@Getter
@Setter
public class AddressRequestDto {

    private String fullName;
    private String mobileNumber;
    private String pinCode;
    private String addressLine1;
    private String addressLine2;
    private String district;
    private String state;

    public static AddressEntity fromAddressRequestDto(AddressRequestDto addressRequestDto) {
        AddressEntity address = new AddressEntity();
        address.setFullName(addressRequestDto.getFullName());
        address.setMobileNumber(addressRequestDto.getMobileNumber());
        address.setPinCode(addressRequestDto.getPinCode());
        address.setAddressLine1(addressRequestDto.getAddressLine1());
        address.setAddressLine2(addressRequestDto.getAddressLine2());
        address.setDistrict(addressRequestDto.getDistrict());
        address.setState(addressRequestDto.getState());
        return address;
    }
}
