package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "libri")
@Entity
public class LiberEntity {

    @Id
    private String isbn;
    private String titulli;

//    @OneToMany
//    @ManyToOne
//    @ManyToMany
//    @OneToOne

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorEntity autor;

}
