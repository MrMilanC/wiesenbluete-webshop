package com.example.wiesenbluetewebshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String role;
    //@JsonIgnore
//    @OneToOne(cascade = CascadeType.ALL)
//    private Cart cart;
}
