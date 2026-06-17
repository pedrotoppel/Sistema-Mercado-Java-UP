package model;

import java.io.Serializable;

/**
 * CLASSE ABSTRATA e SUPERCLASSE (Heranca).
 * Reune os atributos comuns a Cliente e Funcionario.
 * Implementa Serializable para poder ser salva em arquivo.
 */
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    // ENCAPSULAMENTO: atributos privados
    private int id;
    private String nome;
    private String cpf;
    private String telefone;

    public Pessoa(int id, String nome, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    // METODO ABSTRATO: cada filha define o proprio tipo
    public abstract String getTipo();

    // GETTERS e SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
