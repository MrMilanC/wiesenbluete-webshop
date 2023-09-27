package com.example.wiesenbluetewebshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class NewUserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String role = "ROLE_USER";

    //private int cartId;

    public NewUserDTO(){}
}
