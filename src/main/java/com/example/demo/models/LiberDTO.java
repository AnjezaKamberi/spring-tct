package com.example.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;

//@Setter
//@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LiberDTO {

    @NotBlank(message = "Vendos nje titull te plote")
    private String titull;

    @NotBlank(message = "Jep nje isbn te plote")
    @Size(min = 5, max = 13, message = "Isbn duhet t ekete minimumi 5 karaktere dhe max 13 karaktere")
    private String isbn;

//    public LiberDTO() {
//
//    }

//    public String getTitull() {
//        return titull;
//    }
//
//    public void setTitull(String titull) {
//        this.titull = titull;
//    }
//
//    public String getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(String isbn) {
//        this.isbn = isbn;
//    }
}
