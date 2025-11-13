package com.example.appandroid.model;

import com.google.gson.annotations.SerializedName;

public class Empresa {

    @SerializedName("id")
    private String id;

    @SerializedName("nome_fantasia")
    private String nome_fantasia;

    @SerializedName("razao_social")
    private String razao_social;

    @SerializedName("tipo")
    private String tipo;

    public Empresa() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
