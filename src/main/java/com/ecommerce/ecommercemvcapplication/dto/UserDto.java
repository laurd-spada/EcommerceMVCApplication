package com.ecommerce.ecommercemvcapplication.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String mobileNumber;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
