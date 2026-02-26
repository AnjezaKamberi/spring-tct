package com.example.demo.controllers;

import com.example.demo.models.Author;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private List<Author> authors = new ArrayList<>();

    @GetMapping("/all")
    public List<Author> getAuthors (){
        return authors;

    }

    @PostMapping
    public String registerAuthor (@RequestBody Author author){
        authors.add(author);
        return "Regjistrimi u krye me sukses";

    }

    @PutMapping("/{id}")
    public Author modifyAuthor(@PathVariable int id, @RequestBody Author author){
        List<Author> authorsById = authors.stream()
                .filter( a-> a.getId() == id)
                .collect(Collectors.toList());


        for(Author a: authorsById){
           a.setName(author.getName());
           a.setAge(author.getAge());
           a.setSurname(author.getSurname());
        }
        return authorsById.isEmpty() ? null : authorsById.get(0);
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable int id){
        boolean fshi = authors.removeIf(a -> a.getId()== id);
        if(fshi){
            return "Autori u fshi me sukses!";
        } else {
            return "Autori nuk u gjet.";
        }
    }
}
