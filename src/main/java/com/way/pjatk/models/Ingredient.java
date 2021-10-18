package com.way.pjatk.models;

public class Ingredient {
    private String name;
    private Integer calories;
    private Double price;
    private Boolean isVegan;

    public Ingredient() {
    }

    public Ingredient(String name, Integer calories, Double price, Boolean isVegan) {
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.isVegan = isVegan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean isVegan() {
        return isVegan;
    }

    public void setVegan(Boolean vegan) {
        isVegan = vegan;
    }
}
