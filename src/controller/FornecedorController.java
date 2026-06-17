package controller;

import java.util.List;
import model.Fornecedor;
import util.LoggerService;
import util.PersistenciaService;

/**
 * Controller do CRUD de Fornecedores.
 */
public class FornecedorController {

    private List<Fornecedor> fornecedores;
    private int proximoId;
    private final PersistenciaService<Fornecedor> persistencia;

    public FornecedorController() {
        persistencia = new PersistenciaService<Fornecedor>("fornecedores.dat");
        fornecedores = persistencia.carregar();
        proximoId = calcularProximoId();
    }

    private int calcularProximoId() {
        int maior = 0;
        for (Fornecedor f : fornecedores) {
            if (f.getId() > maior) {
                maior = f.getId();
            }
        }
        return maior + 1;
    }

    public Fornecedor cadastrar(String nome, String cnpj) {
        Fornecedor f = new Fornecedor(proximoId, nome, cnpj);
        fornecedores.add(f);
        proximoId++;
        persistencia.salvar(fornecedores);
        LoggerService.log("INFO", "Fornecedor cadastrado: " + nome);
        return f;
    }

    public List<Fornecedor> listar() {
        return fornecedores;
    }

    public Fornecedor buscar(int id) {
        for (Fornecedor f : fornecedores) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public boolean alterar(int id, String nome, String cnpj) {
        Fornecedor f = buscar(id);
        if (f == null) {
            return false;
        }
        f.setNome(nome);
        f.setCnpj(cnpj);
        persistencia.salvar(fornecedores);
        LoggerService.log("INFO", "Fornecedor alterado: ID " + id);
        return true;
    }

    public boolean excluir(int id) {
        Fornecedor f = buscar(id);
        if (f == null) {
            return false;
        }
        fornecedores.remove(f);
        persistencia.salvar(fornecedores);
        LoggerService.log("INFO", "Fornecedor excluido: ID " + id);
        return true;
    }
}
