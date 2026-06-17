package model;

import java.io.Serializable;

public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        setProduto(produto);
        setQuantidade(quantidade);
    }

    // Subtotal do item = preco final do produto x quantidade
    public double getSubtotal() {
        return produto.getPrecoFinal() * quantidade;
    }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
