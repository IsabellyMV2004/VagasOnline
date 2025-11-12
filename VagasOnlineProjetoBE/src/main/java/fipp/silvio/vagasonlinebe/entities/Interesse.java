package fipp.silvio.vagasonlinebe.entities;

import com.google.gson.annotations.SerializedName;
import org.springframework.data.annotation.Id;

public class Interesse {
    @SerializedName("_id")
    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Vaga vaga;

    public Interesse() {}

    public Interesse(String id, String nome, String cpf, String email, String telefone, Vaga vaga) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.vaga = vaga;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Vaga getVaga() { return vaga; }
    public void setVaga(Vaga vaga) { this.vaga = vaga; }
}
