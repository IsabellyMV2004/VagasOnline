package fipp.silvio.vagasonlinebe.entities;

public class Interesse {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Vaga vaga;

    public Interesse() {}

    public Interesse(String nome, String cpf, String email, String telefone, Vaga vaga) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.vaga = vaga;
    }

    // getters e setters
}
