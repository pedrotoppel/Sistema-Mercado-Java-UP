package model;

public class ProdutoAlimenticio extends Produto {

    private static final long serialVersionUID = 1L;

    private String dataValidade;

    public ProdutoAlimenticio(int id, String nome, double preco, int quantidade,
                              Categoria categoria, Fornecedor fornecedor, String dataValidade) {
        super(id, nome, preco, quantidade, categoria, fornecedor);
        this.dataValidade = dataValidade;
    }

    @Override
    public String getTipoProduto() {
        return "Alimenticio";
    }

    @Override
    public double calcularDesconto() {
        return getPreco() * 0.10;
    }

    public String getDataValidade() { return dataValidade; }
    public void setDataValidade(String dataValidade) { this.dataValidade = dataValidade; }
}
