package com.way.pjatk.models;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Sandwich> prepareSandwich() {
        return ResponseEntity.ok(sandwichService.getSandwich("Red"));
    }
}
