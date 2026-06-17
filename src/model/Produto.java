package model;

import java.io.Serializable;
import model.interfaces.Descontavel;

public abstract class Produto implements Serializable, Descontavel {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private Categoria categoria;
    private Fornecedor fornecedor;

    public Produto(int id, String nome, double preco, int quantidade,
                   Categoria categoria, Fornecedor fornecedor) {
        setId(id);
        setNome(nome);
        setPreco(preco);
        setQuantidade(quantidade);
        setCategoria(categoria);
        setFornecedor(fornecedor);
    }

    public abstract String getTipoProduto();

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
