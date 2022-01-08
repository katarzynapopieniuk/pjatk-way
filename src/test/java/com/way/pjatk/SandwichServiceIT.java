package com.way.pjatk;

import com.way.pjatk.models.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SandwichServiceIT {

    @MockBean
    private SandwichRepository sandwichRepository;

    @Autowired
    private SandwichService sandwichService;

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
        Assertions.assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> sandwichService.getFinalPrice(sandwich));
    }

    @Test
    void shouldFindById() {
        Mockito.when(sandwichRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(new Sandwich()));
        int id = 1;
        Sandwich byId = sandwichService.findById(id);
        Assertions.assertThat(byId).isNotNull();
    }

    @Test
    void shouldNotFindById() {
        Mockito.when(sandwichRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.empty());
        int id = 1;
        Sandwich byId = sandwichService.findById(id);
        Assertions.assertThat(byId).isNull();
    }

    @Test
    void shouldReturnTrueIfExists() {
        Mockito.when(sandwichRepository.existsById(ArgumentMatchers.any())).thenReturn(true);
        int id = 1;
        Assertions.assertThat(sandwichService.existsById(id)).isTrue();
    }

    @Test
    void shouldReturnFalseIfNotExists() {
        Mockito.when(sandwichRepository.existsById(ArgumentMatchers.any())).thenReturn(false);
        int id = 1;
        Assertions.assertThat(sandwichService.existsById(id)).isFalse();
    }

    @Test
    void shouldReturnAllSandwiches() {
        List<Sandwich> allSandwiches = List.of(new Sandwich());
        Mockito.when(sandwichRepository.findAll()).thenReturn(allSandwiches);
        Assertions.assertThat(sandwichService.findAll()).isEqualTo(allSandwiches);
    }

    @Test
    void shouldReturnEmptyList() {
        Mockito.when(sandwichRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertThat(sandwichService.findAll()).isEmpty();
    }

    @Test
    void shouldDeleteById() {
        sandwichService.deleteById(1);
        Mockito.verify(sandwichRepository, Mockito.times(1)).deleteById(ArgumentMatchers.any());
    }
}
