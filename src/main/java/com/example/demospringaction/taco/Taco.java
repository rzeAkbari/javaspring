package com.example.demospringaction.taco;

import com.example.demospringaction.ingredients.Ingredients;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;


    private Date createdAt;
    @NotNull
    @Size(min = 3, max = 12, message="Name must be at least 5 characters long")
    private String name;
    @ManyToMany(targetEntity=Ingredients.class)
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredients> ingredients;

    public Taco(String name, List<Ingredients> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }
    public Taco() {

    }
    //You’ll use this to set the createdAt property to the current date and time before Taco is persisted.
    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taco taco = (Taco) o;
        return Objects.equals(name, taco.name) && Objects.equals(ingredients, taco.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ingredients);
    }
}
