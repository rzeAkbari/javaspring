package com.example.demospringaction.ingredients;

import com.example.demospringaction.ingredients.IngredientRepository;
import com.example.demospringaction.ingredients.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class IngredientsRepositoryImpl implements IngredientRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public IngredientsRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredients> findAll() {
        return jdbc.query("Select id, name, type from Ingredients",
                this::mapRowToIngredients);
    }

    @Override
    public Ingredients findOne(String Id) {
        return jdbc.queryForObject("Select id, name, type from Ingredients" +
                                        "where id=?", this::mapRowToIngredients, Id);
    }

    @Override
    public Ingredients save(Ingredients ingredient) {
        jdbc.update("insert into Ingredients (id, name, type)" +
                        " values ? ? ?",
                        ingredient.getId(),
                        ingredient.getName(),
                        ingredient.getType().toString());
        return ingredient;
    }

    private Ingredients mapRowToIngredients(ResultSet rs, int rowNum) throws SQLException {
            return new Ingredients(rs.getLong("id"),
                                    rs.getString("name"),
                                    Ingredients.Type.valueOf(rs.getString("type")));

    }
}
