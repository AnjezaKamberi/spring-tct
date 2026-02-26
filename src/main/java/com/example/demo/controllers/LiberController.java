package com.example.demo.controllers;

import com.example.demo.models.Liber;
import com.example.demo.services.LiberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/liber")
public class LiberController {

    //todo introduce LOGGER

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
    public List<Liber> merrTeGjitheLibrat() {
        return liberService.ktheLibrat();
    }

    @PostMapping
    public Liber shtoLiber(@RequestBody Liber liber) {
        return liberService.shtoLiberTeRi(liber);
    }

    @PutMapping("{isbn}")
    public Liber modifikoLiber(@PathVariable("isbn") String isbn, @RequestBody Liber liber) {
        return liberService.modifikoLiberSipasISBN(isbn, liber);
    }

    @DeleteMapping("{isbn}")
    public boolean fshiLiber(@PathVariable String isbn) {
        return liberService.fshiLiber(isbn);
    }

}
