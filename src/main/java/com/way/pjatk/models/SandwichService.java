package com.way.pjatk.models;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SandwichService {

    private final IngredientRepository ingredientRepository;

    private final SandwichRepository sandwichRepository;

    private static final Map<String, List<Ingredient>> NAME_TO_INGREDIENTS_MAP = new HashMap<>() {{
        put("Green", List.of(new Ingredient(null, "broccoli", 50, 2.49, true)));
        put("Red", List.of(new Ingredient(null, "tomato", 50, 3.49, true)));
    }};

    public SandwichService(IngredientRepository ingredientRepository, SandwichRepository sandwichRepository) {
        this.ingredientRepository = ingredientRepository;
        this.sandwichRepository = sandwichRepository;
    }

    public Sandwich getSandwich(String name) {
        List<Ingredient> ingredients = NAME_TO_INGREDIENTS_MAP.getOrDefault(name, Collections.emptyList());
        return new Sandwich(null, name, null, null, ingredients, SandwichSize.SMALL);
    }

    public Sandwich getExampleSandwich() {
        Ingredient cheese = new Ingredient(null, "cheese", 100, 3.50, false);
        Ingredient broccoli = new Ingredient(null, "broccoli", 50, 2.49, true);
        List<Ingredient> ingredients = List.of(cheese, broccoli);
        Sandwich sandwich = new Sandwich(null, "with cheese", 300, 10.49, ingredients, SandwichSize.BIG);
        sandwichRepository.save(sandwich);
        return sandwich;
    }

    public void addButter() {
        Ingredient butter = new Ingredient(null, "butter", 100, 5.0, false);
        ingredientRepository.save(butter);
    }

    public Collection<Sandwich> getTopFiveSandwiches() {
        List<Integer> ids = List.of(1, 2, 3, 4, 5);
        return sandwichRepository.findAllById(ids);
    }
}
