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

    public Sandwich findById(Integer id) {
        return sandwichRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void addIngredient(Sandwich sandwich, Ingredient ingredient) {
        if(sandwich.getIngredients() != null) {
            sandwich.getIngredients().add(ingredient);
        } else {
            sandwich.setIngredients(List.of(ingredient));
        }
    }

    public void setName(Sandwich sandwich, String name) {
        if(sandwich.getName() != null) {
            sandwich.setName(name);
        }
    }

    public void setBiggerSize(Sandwich sandwich) {
        Optional.ofNullable(sandwich.getSandwichSize())
                .filter(size -> !size.equals(SandwichSize.BIG))
                .map(SandwichSize::next)
                .ifPresent(sandwich::setSandwichSize);
    }

    public void setSmallerSize(Sandwich sandwich) {
        Optional.ofNullable(sandwich.getSandwichSize())
                .filter(size -> !size.equals(SandwichSize.SMALL))
                .map(SandwichSize::previous)
                .ifPresent(sandwich::setSandwichSize);
    }

    public void setNewPriceIfCurrentIsMoreExpensive(Sandwich sandwich, Double newPrice) {
        Optional.ofNullable(sandwich.getBasePrice())
                .filter(price -> price > newPrice)
                .ifPresent(price -> sandwich.setBasePrice(newPrice));
    }

    public Double getFinalPrice(Sandwich sandwich) {
        Double basePrice = sandwich.getBasePrice();
        if(basePrice != null) {
            return basePrice + getIngredientsPrice(sandwich);
        }
        throw new RuntimeException("Base price is null");
    }

    public Double getIngredientsPrice(Sandwich sandwich) {
        List<Ingredient> ingredients = sandwich.getIngredients();
        if(ingredients != null) {
            return ingredients.stream()
                    .map(Ingredient::getPrice)
                    .filter(Objects::nonNull)
                    .reduce(Double::sum)
                    .orElse(0.0);
        } else {
            throw new RuntimeException("Ingredients are null");
        }
    }
}
