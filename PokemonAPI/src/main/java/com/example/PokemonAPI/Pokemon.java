package com.example.PokemonAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    private String name;
    private String busca;
    private String index;
    private List<Types> types;

    public String getBusca() {
        return busca;
    }
    public void setBusca(String busca) {
        this.busca = busca;
    }

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "'"+index +"':" + " {'Busca': " + busca + ",'Nome':" + name + ",'Tipos': [" + types + "]}";
    }
}
