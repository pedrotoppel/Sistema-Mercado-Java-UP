package controller;

import java.util.List;
import model.Categoria;
import model.Fornecedor;
import model.ProdutoAlimenticio;
import model.exceptions.ValidacaoException;
import util.LoggerService;
import util.PersistenciaService;

public class ProdutoAlimenticioController {

    private List<ProdutoAlimenticio> produtos;
    private int proximoId;
    private final PersistenciaService<ProdutoAlimenticio> persistencia;

    public ProdutoAlimenticioController() {
        persistencia = new PersistenciaService<ProdutoAlimenticio>("alimenticios.dat");
        produtos = persistencia.carregar();
        proximoId = calcularProximoId();
    }

    private int calcularProximoId() {
        int maior = 0;
        for (ProdutoAlimenticio p : produtos) {
            if (p.getId() > maior) {
                maior = p.getId();
            }
        }
        return maior + 1;
    }

    private void validar(double preco, int quantidade) throws ValidacaoException {
        if (preco < 0) {
            throw new ValidacaoException("O preco nao pode ser negativo.");
        }
        if (quantidade < 0) {
            throw new ValidacaoException("A quantidade nao pode ser negativa.");
        }
    }

    public ProdutoAlimenticio cadastrar(String nome, double preco, int quantidade,
                                        Categoria categoria, Fornecedor fornecedor,
                                        String dataValidade) throws ValidacaoException {
        validar(preco, quantidade);
        ProdutoAlimenticio p = new ProdutoAlimenticio(proximoId, nome, preco, quantidade,
                categoria, fornecedor, dataValidade);
        produtos.add(p);
        proximoId++;
        persistencia.salvar(produtos);
        LoggerService.log("INFO", "Produto alimenticio cadastrado: " + nome);
        return p;
    }

    public List<ProdutoAlimenticio> listar() {
        return produtos;
    }

    public ProdutoAlimenticio buscar(int id) {
        for (ProdutoAlimenticio p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean alterar(int id, String nome, double preco, int quantidade,
                           Categoria categoria, Fornecedor fornecedor,
                           String dataValidade) throws ValidacaoException {
        validar(preco, quantidade);
        ProdutoAlimenticio p = buscar(id);
        if (p == null) {
            return false;
        }
        p.setNome(nome);
        p.setPreco(preco);
        p.setQuantidade(quantidade);
        p.setCategoria(categoria);
        p.setFornecedor(fornecedor);
        p.setDataValidade(dataValidade);
        persistencia.salvar(produtos);
        LoggerService.log("INFO", "Produto alimenticio alterado: ID " + id);
        return true;
    }

    public boolean excluir(int id) {
        ProdutoAlimenticio p = buscar(id);
        if (p == null) {
            return false;
        }
        produtos.remove(p);
        persistencia.salvar(produtos);
        LoggerService.log("INFO", "Produto alimenticio excluido: ID " + id);
        return true;
    }
}
