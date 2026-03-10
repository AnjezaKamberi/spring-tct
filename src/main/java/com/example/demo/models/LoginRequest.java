package com.example.demo.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email eshte i detyrueshem")
    @Email(message = "Email duhet te jete valid")
    private String email;

    @NotBlank(message = "Fjalekalimi eshte i detyrueshem")
    private String password;
}

