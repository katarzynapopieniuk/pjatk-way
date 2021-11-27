package com.way.pjatk.models;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/sandwich")
public class SandwichRestController {

private final SandwichService sandwichService;

    public SandwichRestController(SandwichService sandwichService) {
        this.sandwichService = sandwichService;
    }

    @GetMapping("/example")
    public ResponseEntity<Sandwich> getExampleSandwich() {
        return ResponseEntity.ok(sandwichService.getExampleSandwich());
    }

    @GetMapping("/prepare")
    public ResponseEntity<Sandwich> prepareSandwich(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(sandwichService.getSandwich(name));
    }

    @GetMapping("/addButter")
    public void addButter() {
        sandwichService.addButter();
    }

    @GetMapping("/topFiveSandwiches")
    public ResponseEntity<Collection<Sandwich>> getTopFiveSandwiches() {
        return ResponseEntity.ok(sandwichService.getTopFiveSandwiches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sandwich> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(sandwichService.findById(id));
    }
}
