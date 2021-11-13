package com.way.pjatk.models;

import javax.persistence.*;

@Entity
@Table(name = "Ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer calories;
    private Double price;
    private Boolean isVegan;

    @ManyToOne
    private Sandwich sandwich;

    public Ingredient() {
    }

    public Ingredient(Integer id, String name, Integer calories, Double price, Boolean isVegan) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.isVegan = isVegan;
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
