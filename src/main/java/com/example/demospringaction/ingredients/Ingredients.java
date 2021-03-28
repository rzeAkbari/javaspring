package com.example.demospringaction.ingredients;

import java.util.Objects;

public class Ingredients {
    private Long Id;
    private String name;
    private Type type;

    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    public Ingredients(Long id, String name, Type type) {
        Id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredients that = (Ingredients) o;
        return Objects.equals(Id, that.Id) && Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name, type);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
