package com.example.demo.controllers;

import com.example.demo.models.AuthorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private List<AuthorDTO> authorDTOS = new ArrayList<>();

    @GetMapping("/all")
    public List<AuthorDTO> getAuthors (){
        return authorDTOS;

    }

    @PostMapping
    public ResponseEntity<String> registerAuthor (@RequestBody AuthorDTO authorDTO){
        authorDTOS.add(authorDTO);
        return ResponseEntity.ok("Regjistrimi u krye me sukses");

    }

    @PutMapping("/{id}")
    public AuthorDTO modifyAuthor(@PathVariable int id, @RequestBody AuthorDTO authorDTO){
        List<AuthorDTO> authorsById = authorDTOS.stream()
                .filter( a-> a.getId() == id)
                .collect(Collectors.toList());


        for(AuthorDTO a: authorsById){
           a.setName(authorDTO.getName());
           a.setAge(authorDTO.getAge());
           a.setSurname(authorDTO.getSurname());
        }
        return authorsById.isEmpty() ? null : authorsById.get(0);
    }

    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable int id){
        boolean fshi = authorDTOS.removeIf(a -> a.getId()== id);
        if(fshi){
            return "Autori u fshi me sukses!";
        } else {
            return "Autori nuk u gjet.";
        }
    }
}
