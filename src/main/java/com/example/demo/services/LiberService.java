package com.example.demo.services;

import com.example.demo.entities.LiberEntity;
import com.example.demo.exceptions.LiberIsbnExistsException;
import com.example.demo.exceptions.LiberNotFoundException;
import com.example.demo.mappers.LiberMapper;
import com.example.demo.models.LiberDTO;
import com.example.demo.repositories.LiberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LiberService {

    Logger log = LoggerFactory.getLogger(LiberService.class);

    private final LiberRepository liberRepository;
    private final LiberMapper liberMapper;
    // INFO
    // WARN
    // DEBUG
    // ERROR
//    private List<LiberDTO> libra = new ArrayList<>();

    public List<LiberDTO> ktheLibrat() {
        log.info("Implementimi i servisit per terheqjen e te gjithe librave");
//        verifikoListeLibraInicializuar();

        // findAll - SELECT * FROM LIBER
        List<LiberEntity> libra = liberRepository.findAll();

        /*List<LiberDTO> libraResponse = new ArrayList<>();

        for (LiberEntity liberEntity : libra) {
            LiberDTO liberResponse = new LiberDTO();
            liberResponse.setIsbn(liberEntity.getIsbn());
            liberResponse.setTitull(liberEntity.getTitulli());
            libraResponse.add(liberResponse);
        }*/

        return liberMapper.toDTOList(libra);
    }

    public LiberDTO shtoLiberTeRi(LiberDTO liberDTO) {

//        Optional<LiberEntity> liberIsbnExists = liberRepository.findById(liberDTO.getIsbn());
        boolean liberIsbnExists = liberRepository.existsById(liberDTO.getIsbn());

        if (liberIsbnExists) {
            throw new LiberIsbnExistsException("Libri me isbn " + liberDTO.getIsbn() + " ekziston. Provo perseri!");
        }

        LiberEntity liberToSave = liberMapper.toEntity(liberDTO);
        LiberEntity liberRuajtur = liberRepository.save(liberToSave);

        return liberMapper.toDTO(liberRuajtur);
    }

    public LiberDTO modifikoLiberSipasISBN(String isbn, LiberDTO liberDTOModifikuar) {
        LiberDTO liberToModify = merrLiberPerISBNEkzistues(isbn);
        liberToModify.setTitull(liberDTOModifikuar.getTitull());

        LiberEntity modifiedLiber = liberRepository.save(liberMapper.toEntity(liberToModify));
        return liberMapper.toDTO(modifiedLiber);
    }

    public boolean fshiLiber(String isbn) {
        if (liberRepository.existsById(isbn)) {
            liberRepository.deleteById(isbn);
            return true;
        }
        throw new LiberNotFoundException("Libri me isbn " + isbn + " nuk gjendet");
    }

//    private void verifikoListeLibraInicializuar() {
//        if (libra == null) {
//            log.info("Lista e librave akoma nuk eshte inicializuar");
//            libra = new ArrayList<>();
//            log.info("Lista e librave u inicializua");
//        }
//    }

    /*private LiberDTO merrLiberPerISBNEkzistues(String isbn) {
        LiberDTO liberDTOEkzistues = null;
        for (LiberDTO l : libra) {
            if (l.getIsbn().equals(isbn)) {
                liberDTOEkzistues = l;
                break;
            }
        }

        if (liberDTOEkzistues == null) {
            throw new LiberNotFoundException("Libri me isbn " + isbn + " nuk gjendet");
        }

        return liberDTOEkzistues;
    } */

    private LiberDTO merrLiberPerISBNEkzistues(String isbn) {
        Optional<LiberEntity> savedLiber = liberRepository.findById(isbn);
        if (savedLiber.isEmpty()) {
            throw new LiberNotFoundException("Libri me isbn " + isbn + " nuk gjendet");
        }

        return liberMapper.toDTO(savedLiber.get());
    }
}
