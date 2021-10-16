package com.way.pjatk.models;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sandwich")
public class SandwichRestController {

    @GetMapping("/example")
    public ResponseEntity<Sandwich> getExampleSandwich() {
        Ingredient cheese = new Ingredient("cheese", 100, 3.50, false);
        Ingredient broccoli = new Ingredient("broccoli", 50, 2.49, true);
        List<Ingredient> ingredients = List.of(cheese, broccoli);
        Sandwich sandwich = new Sandwich(null, "with cheese", 300, 10.49, ingredients, SandwichSize.BIG);

        return ResponseEntity.ok(sandwich);
    }

    @GetMapping("/nullexample")
    public ResponseEntity<Sandwich> getNullExampleSandwich() {
        Sandwich sandwich = new Sandwich(null, "with cheese", 300, 10.49, null, SandwichSize.BIG);

        return ResponseEntity.ok(sandwich);
    }
}
