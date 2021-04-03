package com.example.demospringaction.ingredients;

import com.example.demospringaction.ingredients.Ingredients;

public interface IngredientRepository {
    Iterable<Ingredients> findAll();
    Ingredients findOne(String Id);
    Ingredients save(Ingredients ingredient);
}
