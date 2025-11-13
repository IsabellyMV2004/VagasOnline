package com.example.appandroid.model;

import com.google.gson.annotations.SerializedName;

public class Vaga {

    @SerializedName("id")
    private String id;

    @SerializedName("registro")
    private String registro;

    @SerializedName("cidade")
    private String cidade;

    @SerializedName("estado")
    private String estado;

    @SerializedName("pre_requisitos")
    private String preRequisitos;

    @SerializedName("formacao")
    private String formacao;

    @SerializedName("conhecimentos_requeridos")
    private String conhecimentosRequeridos;

    @SerializedName("regime")
    private String regime;

    @SerializedName("jornada_trabalho")
    private String jornadaTrabalho;

    @SerializedName("remuneracao")
    private String remuneracao;

    @SerializedName("empresa")
    private Empresa empresa;

    @SerializedName("cargo")
    private String cargo;

    public Vaga() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPreRequisitos() {
        return preRequisitos;
    }

    public void setPreRequisitos(String preRequisitos) {
        this.preRequisitos = preRequisitos;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getConhecimentosRequeridos() {
        return conhecimentosRequeridos;
    }

    public void setConhecimentosRequeridos(String conhecimentosRequeridos) {
        this.conhecimentosRequeridos = conhecimentosRequeridos;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getJornadaTrabalho() {
        return jornadaTrabalho;
    }

    public void setJornadaTrabalho(String jornadaTrabalho) {
        this.jornadaTrabalho = jornadaTrabalho;
    }

    public String getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(String remuneracao) {
        this.remuneracao = remuneracao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
