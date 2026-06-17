package model;

import java.io.Serializable;

/**
 * Categoria de um produto (ex.: Bebidas, Higiene).
 * Existe de forma independente -> usada em AGREGACAO com Produto.
 */
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return "[" + id + "] " + nome;
    }
}
