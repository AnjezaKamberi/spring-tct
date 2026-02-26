package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tct")
public class TCTController {

    // API - Hello world
//    @RequestMapping(method = RequestMethod.GET, value = "/hello-world")
    @GetMapping("/hello-world")
    public String helloWorldSpring() {
        return "Welcome to SPRING world";
    }

    // /URL?key=value&key2=value2
    // perdorimi i request param (opsionale)
    @GetMapping("/say-hello")
    public String sayHelloTo(@RequestParam(value = "name") String name) {
        return "Welcome to SPRING " + name;
    }

    // perdorimi i path variable (detyrueshme)
    @GetMapping("/favorite-student/{id}/tct-2026")
    public String welcomeToFavoriteStudent(@PathVariable("id") Long id) {
        return "Welcome to SPRING fav Student " + id + "******";
    }

}
