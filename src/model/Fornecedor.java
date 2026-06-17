package model;

import java.io.Serializable;

public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String cnpj;

    public Fornecedor(int id, String nome, String cnpj) {
        setId(id);
        setNome(nome);
        setCnpj(cnpj);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " (CNPJ: " + cnpj + ")";
    }
}
