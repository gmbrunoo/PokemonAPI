package com.example.PokemonAPI;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Type {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "'Tipo': " + name + "";
    }

}
