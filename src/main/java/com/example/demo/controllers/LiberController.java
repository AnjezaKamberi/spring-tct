package com.example.demo.controllers;

import com.example.demo.models.Liber;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/liber")
public class LiberController {

    private List<Liber> libra = new ArrayList<>();

    @GetMapping("/all")
    public List<Liber> merrTeGjitheLibrat() {
        return libra;
    }

    @PostMapping
    public Liber shtoLiber(@RequestBody Liber liber) {
        libra.add(liber);
        return liber;
    }

    @PutMapping("{isbn}")
    public Liber modifikoLiber(@PathVariable("isbn") String isbn, @RequestBody Liber liber) {
        List<Liber> liberPerIsbn = libra.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .collect(Collectors.toList());

        if (liberPerIsbn.isEmpty()) {
            return null;
        }

        for (Liber l : liberPerIsbn) {
            l.setTitull(liber.getTitull());
        }
        return liberPerIsbn.get(0);
    }

    @DeleteMapping("{isbn}")
    public List<Liber>  fshiLiber(@PathVariable String isbn) {
        List<Liber> liberPerIsbn = libra.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .collect(Collectors.toList());

        if (liberPerIsbn.isEmpty()) {
            return null;
        }

        libra.removeAll(liberPerIsbn);

        return libra;
    }
}
