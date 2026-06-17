package model;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String cpf;
    private String telefone;

    public Pessoa(int id, String nome, String cpf, String telefone) {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
    }

    public abstract String getTipo();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
