package controller;

import java.util.List;
import model.Categoria;
import model.Fornecedor;
import model.ProdutoLimpeza;
import model.exceptions.ValidacaoException;
import util.LoggerService;
import util.PersistenciaService;

public class ProdutoLimpezaController {

    private List<ProdutoLimpeza> produtos;
    private int proximoId;
    private final PersistenciaService<ProdutoLimpeza> persistencia;

    public ProdutoLimpezaController() {
        persistencia = new PersistenciaService<ProdutoLimpeza>("limpeza.dat");
        produtos = persistencia.carregar();
        proximoId = calcularProximoId();
    }

    private int calcularProximoId() {
        int maior = 0;
        for (ProdutoLimpeza p : produtos) {
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

    public ProdutoLimpeza cadastrar(String nome, double preco, int quantidade,
                                    Categoria categoria, Fornecedor fornecedor,
                                    boolean toxico) throws ValidacaoException {
        validar(preco, quantidade);
        ProdutoLimpeza p = new ProdutoLimpeza(proximoId, nome, preco, quantidade,
                categoria, fornecedor, toxico);
        produtos.add(p);
        proximoId++;
        persistencia.salvar(produtos);
        LoggerService.log("INFO", "Produto de limpeza cadastrado: " + nome);
        return p;
    }

    public List<ProdutoLimpeza> listar() {
        return produtos;
    }

    public ProdutoLimpeza buscar(int id) {
        for (ProdutoLimpeza p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean alterar(int id, String nome, double preco, int quantidade,
                           Categoria categoria, Fornecedor fornecedor,
                           boolean toxico) throws ValidacaoException {
        validar(preco, quantidade);
        ProdutoLimpeza p = buscar(id);
        if (p == null) {
            return false;
        }
        p.setNome(nome);
        p.setPreco(preco);
        p.setQuantidade(quantidade);
        p.setCategoria(categoria);
        p.setFornecedor(fornecedor);
        p.setToxico(toxico);
        persistencia.salvar(produtos);
        LoggerService.log("INFO", "Produto de limpeza alterado: ID " + id);
        return true;
    }

    public boolean excluir(int id) {
        ProdutoLimpeza p = buscar(id);
        if (p == null) {
            return false;
        }
        produtos.remove(p);
        persistencia.salvar(produtos);
        LoggerService.log("INFO", "Produto de limpeza excluido: ID " + id);
        return true;
    }
}
