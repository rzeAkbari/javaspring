package com.example.demospringaction.ingredients;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class Ingredients {
    @Id
    private final Long Id;
    private final String name;
    private final Type type;

    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
    //JPA requires that entities have a no-arguments constructor,You don’t want to be able to use it, though, so you make it private by setting the access attribute to AccessLevel.PRIVATE. And because there are final properties that must be set, you also set the force attribute to true, which results in the Lombok-generated constructor setting them to null.
    private Ingredients() {
        Id=null;
        name=null;
        type=null;
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


    public String getName() {
        return name;
    }


    public Type getType() {
        return type;
    }

}
