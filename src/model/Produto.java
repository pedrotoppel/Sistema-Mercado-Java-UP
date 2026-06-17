package model;

import java.io.Serializable;
import model.interfaces.Descontavel;

public abstract class Produto implements Serializable, Descontavel {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private Categoria categoria;   // AGREGACAO
    private Fornecedor fornecedor; // AGREGACAO

    public Produto(int id, String nome, double preco, int quantidade,
                   Categoria categoria, Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }

    // METODO ABSTRATO: cada tipo de produto informa seu tipo
    public abstract String getTipoProduto();

    // Preco final ja com o desconto aplicado (usa o metodo da interface)
    public double getPrecoFinal() {
        return preco - calcularDesconto();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }

    @Override
    public String toString() {
        String cat = (categoria == null) ? "sem categoria" : categoria.getNome();
        return "[" + id + "] " + nome + " | " + getTipoProduto()
                + " | R$ " + String.format("%.2f", preco)
                + " | desconto R$ " + String.format("%.2f", calcularDesconto())
                + " | final R$ " + String.format("%.2f", getPrecoFinal())
                + " | qtd: " + quantidade
                + " | categoria: " + cat;
    }
}
