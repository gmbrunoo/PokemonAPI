package com.example.PokemonAPI;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Types {
    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ""+ type +"";
    }

}
