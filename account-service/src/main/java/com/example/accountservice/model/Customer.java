package com.example.accountservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public  class Customer{

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

}