package com.way.pjatk.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Sandwich")
public class Sandwich {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer baseCalories;
    private Double basePrice;
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Ingredient> ingredients;
    private SandwichSize sandwichSize;

    public Sandwich() {
        ingredients = new ArrayList<>();
    }

    public Sandwich(Integer id, String name, Integer baseCalories, Double basePrice, List<Ingredient> ingredients, SandwichSize sandwichSize) {
        this.id = id;
        this.name = name;
        this.baseCalories = baseCalories;
        this.basePrice = basePrice;
        this.ingredients = ingredients;
        this.sandwichSize = sandwichSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseCalories() {
        return baseCalories;
    }

    public void setBaseCalories(Integer baseCalories) {
        this.baseCalories = baseCalories;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public SandwichSize getSandwichSize() {
        return sandwichSize;
    }

    public void setSandwichSize(SandwichSize sandwichSize) {
        this.sandwichSize = sandwichSize;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addIngredients(List<Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    @Override
    public String toString() {
        return "Sandwich: " + name + ", price: " + basePrice;
    }
}
