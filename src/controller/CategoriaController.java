package controller;

import java.util.List;
import model.Categoria;
import util.LoggerService;
import util.PersistenciaService;

public class CategoriaController {

    private List<Categoria> categorias;
    private int proximoId;
    private final PersistenciaService<Categoria> persistencia;

    public CategoriaController() {
        persistencia = new PersistenciaService<Categoria>("categorias.dat");
        categorias = persistencia.carregar();
        proximoId = calcularProximoId();
    }

    private int calcularProximoId() {
        int maior = 0;
        for (Categoria c : categorias) {
            if (c.getId() > maior) {
                maior = c.getId();
            }
        }
        return maior + 1;
    }

    public Categoria cadastrar(String nome) {
        Categoria c = new Categoria(proximoId, nome);
        categorias.add(c);
        proximoId++;
        persistencia.salvar(categorias);
        LoggerService.log("INFO", "Categoria cadastrada: " + nome);
        return c;
    }

    public List<Categoria> listar() {
        return categorias;
    }

    public Categoria buscar(int id) {
        for (Categoria c : categorias) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean alterar(int id, String nome) {
        Categoria c = buscar(id);
        if (c == null) {
            return false;
        }
        c.setNome(nome);
        persistencia.salvar(categorias);
        LoggerService.log("INFO", "Categoria alterada: ID " + id);
        return true;
    }

    public boolean excluir(int id) {
        Categoria c = buscar(id);
        if (c == null) {
            return false;
        }
        categorias.remove(c);
        persistencia.salvar(categorias);
        LoggerService.log("INFO", "Categoria excluida: ID " + id);
        return true;
    }
}
