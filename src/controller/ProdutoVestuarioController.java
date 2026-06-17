package controller;

import java.util.List;
import model.Categoria;
import model.Fornecedor;
import model.ProdutoVestuario;
import model.exceptions.ValidacaoException;
import util.LoggerService;
import util.PersistenciaService;

public class ProdutoVestuarioController {

    private List<ProdutoVestuario> produtos;
    private int proximoId;
    private final PersistenciaService<ProdutoVestuario> persistencia;

    public ProdutoVestuarioController() {
        persistencia = new PersistenciaService<ProdutoVestuario>("vestuario.dat");
        produtos = persistencia.carregar();
        proximoId = calcularProximoId();
    }

    private int calcularProximoId() {
        int maior = 0;
        for (ProdutoVestuario p : produtos) {
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

    public ProdutoVestuario cadastrar(String nome, double preco, int quantidade,
                                      Categoria categoria, Fornecedor fornecedor,
                                      String tamanho) throws ValidacaoException {
        validar(preco, quantidade);
        ProdutoVestuario p = new ProdutoVestuario(proximoId, nome, preco, quantidade,
                categoria, fornecedor, tamanho);
        produtos.add(p);
        proximoId++;
        persistencia.salvar(produtos);
        LoggerService.log("INFO", "Produto de vestuario cadastrado: " + nome);
        return p;
    }

    public List<ProdutoVestuario> listar() {
        return produtos;
    }

    public ProdutoVestuario buscar(int id) {
        for (ProdutoVestuario p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean alterar(int id, String nome, double preco, int quantidade,
                           Categoria categoria, Fornecedor fornecedor,
                           String tamanho) throws ValidacaoException {
        validar(preco, quantidade);
        ProdutoVestuario p = buscar(id);
        if (p == null) {
            return false;
        }
        p.setNome(nome);
        p.setPreco(preco);
        p.setQuantidade(quantidade);
        p.setCategoria(categoria);
        p.setFornecedor(fornecedor);
        p.setTamanho(tamanho);
        persistencia.salvar(produtos);
        LoggerService.log("INFO", "Produto de vestuario alterado: ID " + id);
        return true;
    }

    public boolean excluir(int id) {
        ProdutoVestuario p = buscar(id);
        if (p == null) {
            return false;
        }
        produtos.remove(p);
        persistencia.salvar(produtos);
        LoggerService.log("INFO", "Produto de vestuario excluido: ID " + id);
        return true;
    }
}
