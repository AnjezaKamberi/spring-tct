package com.example.demo.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank(message = "Email eshte i detyrueshem")
    @Email(message = "Email duhet te jete valid")
    private String email;

    @NotBlank(message = "Fjalekalimi eshte i detyrueshem")
    @Size(min = 6, message = "Fjalekalimi duhet te kete te pakten 6 karaktere")
    private String password;

    @NotBlank(message = "Emri eshte i detyrueshem")
    private String name;

    private String role;
}

