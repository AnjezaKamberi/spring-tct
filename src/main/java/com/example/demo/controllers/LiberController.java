package com.example.demo.controllers;

import com.example.demo.models.LiberDTO;
import com.example.demo.services.LiberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/liber")
public class LiberController {

    // FIELD INJECTION
//    @Autowired
//    private LiberService liberService;
    // METHOD INJECTION
    //@Autowired
//    public void setLiberService(LiberService liberService) {
//        this.liberService = liberService;
//    }

    //    private LiberService liberService = new LiberService();
    private final LiberService liberService;

    // CONSTRUCTOR INJECTION
    public LiberController(LiberService liberService) {
        this.liberService = liberService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LiberDTO>> merrTeGjitheLibrat() {
        return ResponseEntity.ok(liberService.ktheLibrat());
    }

    @PostMapping
    public ResponseEntity<LiberDTO> shtoLiber(@Valid @RequestBody LiberDTO liberDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(liberService.shtoLiberTeRi(liberDTO));
    }

    @PutMapping("{isbn}")
    public ResponseEntity<LiberDTO> modifikoLiber(@PathVariable("isbn") String isbn, @RequestBody LiberDTO liberDTO) {
        return ResponseEntity.ok(liberService.modifikoLiberSipasISBN(isbn, liberDTO));
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Void> fshiLiber(@PathVariable String isbn) {
        liberService.fshiLiber(isbn);
        return ResponseEntity
                .noContent()
                .build();
    }

}
