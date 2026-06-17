package model;

public class ProdutoVestuario extends Produto {

    private static final long serialVersionUID = 1L;

    private String tamanho;

    public ProdutoVestuario(int id, String nome, double preco, int quantidade,
                            Categoria categoria, Fornecedor fornecedor, String tamanho) {
        super(id, nome, preco, quantidade, categoria, fornecedor);
        this.tamanho = tamanho;
    }

    @Override
    public String getTipoProduto() {
        return "Vestuario";
    }

    @Override
    public double calcularDesconto() {
        return getPreco() * 0.15;
    }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
}
