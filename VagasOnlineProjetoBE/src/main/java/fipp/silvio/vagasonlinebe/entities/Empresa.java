package fipp.silvio.vagasonlinebe.entities;

import com.google.gson.annotations.SerializedName;
import org.springframework.data.annotation.Id;

public class Empresa {
    @SerializedName("_id")
    private String id;
    private String nome_fantasia;
    private String razao_social;
    private String tipo;

    public Empresa() {
        this("", "", "", "");
    }

    public Empresa(String id, String nome_fantasia, String razao_social, String tipo) {
        this.id = id;
        this.nome_fantasia = nome_fantasia;
        this.razao_social = razao_social;
        this.tipo = tipo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome_fantasia() { return nome_fantasia; }
    public void setNome_fantasia(String nome_fantasia) { this.nome_fantasia = nome_fantasia; }

    public String getRazao_social() { return razao_social; }
    public void setRazao_social(String razao_social) { this.razao_social = razao_social; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
