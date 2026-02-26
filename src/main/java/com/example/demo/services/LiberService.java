package com.example.demo.services;

import com.example.demo.exceptions.LiberIsbnExistsException;
import com.example.demo.exceptions.LiberNotFoundException;
import com.example.demo.models.Liber;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LiberService {

    private List<Liber> libra = new ArrayList<>();

    public List<Liber> ktheLibrat() {
        System.out.print("Implementimi i servisit per terheqjen e te gjithe librave");
        verifikoListeLibraInicializuar();
        return libra;
    }

    public Liber shtoLiberTeRi(Liber liber) {
        verifikoListeLibraInicializuar();
        for (Liber l : libra) {
            if (l.getIsbn().equals(liber.getIsbn())) {
                throw new LiberIsbnExistsException("Libri me isbn " + l.getIsbn() + " ekziston. Provo perseri!");
            }
        }

        libra.add(liber);
        return liber;
    }

    public Liber modifikoLiberSipasISBN(String isbn, Liber liberModifikuar) {
        Liber liberEkzistues = merrLiberPerISBNEkzistues(isbn);
        liberEkzistues.setTitull(liberModifikuar.getTitull());

        return liberModifikuar;
    }

    public boolean fshiLiber(String isbn) {
        Liber liberEkzistues = merrLiberPerISBNEkzistues(isbn);
        return libra.remove(liberEkzistues);
    }

    private void verifikoListeLibraInicializuar() {
        if (libra == null) {
            System.out.printf("Lista e librave akoma nuk eshte inicializuar");
            libra = new ArrayList<>();
            System.out.printf("Lista e librave u inicializua");
        }
    }

    private Liber merrLiberPerISBNEkzistues(String isbn) {
        Liber liberEkzistues = null;
        for (Liber l : libra) {
            if (l.getIsbn().equals(isbn)) {
                liberEkzistues = l;
                break;
            }
        }

        if (liberEkzistues == null) {
            throw new LiberNotFoundException("Libri me isbn " + isbn + " nuk gjendet");
        }

        return liberEkzistues;
    }

}
