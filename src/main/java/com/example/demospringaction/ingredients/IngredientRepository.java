package com.example.demospringaction.ingredients;

import com.example.demospringaction.ingredients.Ingredients;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredients, Long> {
    Iterable<Ingredients> findAll();
    Ingredients save(Ingredients ingredient);
}
