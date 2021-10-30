package com.way.pjatk.models;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SandwichService {

    private static final Map<String, List<Ingredient>> NAME_TO_INGREDIENTS_MAP = new HashMap<>() {{
        put("Green", List.of(new Ingredient(null, "broccoli", 50, 2.49, true)));
        put("Red", List.of(new Ingredient(null, "tomato", 50, 3.49, true)));
    }};

    public Sandwich getSandwich(String name) {
        List<Ingredient> ingredients = NAME_TO_INGREDIENTS_MAP.getOrDefault(name, Collections.emptyList());
        return new Sandwich(null, name, null, null, ingredients, SandwichSize.SMALL);
    }

    public Sandwich getExampleSandwich() {
        Ingredient cheese = new Ingredient(null, "cheese", 100, 3.50, false);
        Ingredient broccoli = new Ingredient(null, "broccoli", 50, 2.49, true);
        List<Ingredient> ingredients = List.of(cheese, broccoli);
        return new Sandwich(null, "with cheese", 300, 10.49, ingredients, SandwichSize.BIG);
    }
}
