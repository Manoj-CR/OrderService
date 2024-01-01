package org.example.orderservice.dto;

import jakarta.persistence.*;
import lombok.NonNull;

import java.util.Set;

public class UserDTO {

    private Long id;
    private String username;

    private String password;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
}
