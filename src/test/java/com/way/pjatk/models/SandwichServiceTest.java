package com.way.pjatk.models;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Collections;
import java.util.List;

class SandwichServiceTest {

    @InjectMocks
    private final SandwichService sandwichService = new SandwichService(null, null);

    @Test
    void shouldChangeName() {
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, Collections.emptyList(), SandwichSize.NORMAL);
        String newName = "newName";
        sandwichService.setName(sandwich, newName);
        Assertions.assertThat(sandwich.getName()).isEqualTo(newName);
    }

    @Test
    void shouldLeaveNullName() {
        Sandwich sandwich = new Sandwich(null, null, 1, 2.0, Collections.emptyList(), SandwichSize.NORMAL);
        String newName = "newName";
        sandwichService.setName(sandwich, newName);
        Assertions.assertThat(sandwich.getName()).isNull();
    }

    @Test
    void shouldSetBiggerSize() {
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, Collections.emptyList(), SandwichSize.NORMAL);
        sandwichService.setBiggerSize(sandwich);
        Assertions.assertThat(sandwich.getSandwichSize()).isEqualTo(SandwichSize.BIG);
    }

    @Test
    void shouldNotChangeSizeIfIsBig() {
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, Collections.emptyList(), SandwichSize.BIG);
        sandwichService.setBiggerSize(sandwich);
        Assertions.assertThat(sandwich.getSandwichSize()).isEqualTo(SandwichSize.BIG);
    }

    @Test
    void shouldNotSetBiggerSizeIfIsNull() {
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, Collections.emptyList(), null);
        sandwichService.setBiggerSize(sandwich);
        Assertions.assertThat(sandwich.getSandwichSize()).isNull();
    }

    @Test
    void shouldSetSmallerSize() {
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, Collections.emptyList(), SandwichSize.NORMAL);
        sandwichService.setSmallerSize(sandwich);
        Assertions.assertThat(sandwich.getSandwichSize()).isEqualTo(SandwichSize.SMALL);
    }

    @Test
    void shouldNotChangeSizeIfIsSmall() {
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, Collections.emptyList(), SandwichSize.SMALL);
        sandwichService.setSmallerSize(sandwich);
        Assertions.assertThat(sandwich.getSandwichSize()).isEqualTo(SandwichSize.SMALL);
    }

    @Test
    void shouldNotSetSmallerSizeIfIsNull() {
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, Collections.emptyList(), null);
        sandwichService.setSmallerSize(sandwich);
        Assertions.assertThat(sandwich.getSandwichSize()).isNull();
    }

    @Test
    void shouldSetNewPrice() {
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, Collections.emptyList(), SandwichSize.SMALL);
        Double newPrice = 1.0;
        sandwichService.setNewPriceIfCurrentIsMoreExpensive(sandwich, newPrice);
        Assertions.assertThat(sandwich.getBasePrice()).isEqualTo(newPrice);
    }

    @Test
    void shouldNotSetNewPrice() {
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, Collections.emptyList(), SandwichSize.SMALL);
        Double newPrice = 3.0;
        sandwichService.setNewPriceIfCurrentIsMoreExpensive(sandwich, newPrice);
        Assertions.assertThat(sandwich.getBasePrice()).isEqualTo(2.0);
    }

    @Test
    void shouldLeaveNullPrice() {
        Sandwich sandwich = new Sandwich(null, "testName", 1, null, Collections.emptyList(), SandwichSize.SMALL);
        Double newPrice = 3.0;
        sandwichService.setNewPriceIfCurrentIsMoreExpensive(sandwich, newPrice);
        Assertions.assertThat(sandwich.getBasePrice()).isNull();
    }

    @Test
    void shouldGetIngredientsPrice() {
        Ingredient ingredient1 = new Ingredient(null, null, null, 3.0, false);
        Ingredient ingredient2 = new Ingredient(null, null, null, 3.0, false);
        Ingredient ingredient3 = new Ingredient(null, null, null, 3.0, false);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2, ingredient3);
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, ingredients, SandwichSize.SMALL);
        Assertions.assertThat(sandwichService.getIngredientsPrice(sandwich)).isEqualTo(9.0);
    }

    @Test
    void shouldGetNotNullIngredientsPrice() {
        Ingredient ingredient1 = new Ingredient(null, null, null, 3.0, false);
        Ingredient ingredient2 = new Ingredient(null, null, null, null, false);
        Ingredient ingredient3 = new Ingredient(null, null, null, 3.0, false);
        List<Ingredient> ingredients = List.of(ingredient1, ingredient2, ingredient3);
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, ingredients, SandwichSize.SMALL);
        Assertions.assertThat(sandwichService.getIngredientsPrice(sandwich)).isEqualTo(6.0);
    }

    @Test
    void shouldThrowExceptionIfIngredientsAreNull() {
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, null, SandwichSize.SMALL);
        Assertions.assertThatThrownBy(() -> sandwichService.getIngredientsPrice(sandwich)).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void shouldGetFinalPrice() {
        List<Ingredient> ingredients = List.of(new Ingredient(null, null, null, 3.0, false));
        Sandwich sandwich = new Sandwich(null, "testName", 1, 2.0, ingredients, SandwichSize.SMALL);
        Assertions.assertThat(sandwichService.getFinalPrice(sandwich)).isEqualTo(5.0);
    }

    @Test
    void shouldThrowExceptionIfBasePriceIsNull() {
        List<Ingredient> ingredients = List.of(new Ingredient(null, null, null, 3.0, false));
        Sandwich sandwich = new Sandwich(null, "testName", 1, null, ingredients, SandwichSize.SMALL);
        Assertions.assertThatThrownBy(() -> sandwichService.getFinalPrice(sandwich)).isExactlyInstanceOf(RuntimeException.class);
    }
}