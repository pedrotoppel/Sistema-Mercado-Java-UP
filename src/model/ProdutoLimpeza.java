package model;

public class ProdutoLimpeza extends Produto {

    private static final long serialVersionUID = 1L;

    private boolean toxico;

    public ProdutoLimpeza(int id, String nome, double preco, int quantidade,
                          Categoria categoria, Fornecedor fornecedor, boolean toxico) {
        super(id, nome, preco, quantidade, categoria, fornecedor);
        setToxico(toxico);
    }

    @Override
    public String getTipoProduto() {
        return "Limpeza";
    }

    @Override
    public double calcularDesconto() {
        return getPreco() * 0.05;
    }

    public boolean isToxico() { return toxico; }
    public void setToxico(boolean toxico) { this.toxico = toxico; }
}
