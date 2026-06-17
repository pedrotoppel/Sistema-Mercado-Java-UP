package controller;

import java.util.List;
import model.Cliente;
import util.LoggerService;
import util.PersistenciaService;

public class ClienteController {

    private List<Cliente> clientes;
    private int proximoId;
    private final PersistenciaService<Cliente> persistencia;

    public ClienteController() {
        persistencia = new PersistenciaService<Cliente>("clientes.dat");
        clientes = persistencia.carregar();
        proximoId = calcularProximoId();
    }

    private int calcularProximoId() {
        int maior = 0;
        for (Cliente c : clientes) {
            if (c.getId() > maior) {
                maior = c.getId();
            }
        }
        return maior + 1;
    }

    // CREATE
    public Cliente cadastrar(String nome, String cpf, String telefone, String email) {
        Cliente cliente = new Cliente(proximoId, nome, cpf, telefone, email);
        clientes.add(cliente);
        proximoId++;
        persistencia.salvar(clientes);
        LoggerService.log("INFO", "Cliente cadastrado: " + nome + " (ID " + cliente.getId() + ")");
        return cliente;
    }

    // READ (listagem)
    public List<Cliente> listar() {
        return clientes;
    }

    // SOBRECARGA 1: buscar por ID
    public Cliente buscar(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    // SOBRECARGA 2: buscar por nome (mesmo nome de metodo, parametro diferente)
    public Cliente buscar(String nome) {
        for (Cliente c : clientes) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    // UPDATE
    public boolean alterar(int id, String nome, String cpf, String telefone, String email) {
        Cliente c = buscar(id);
        if (c == null) {
            return false;
        }
        c.setNome(nome);
        c.setCpf(cpf);
        c.setTelefone(telefone);
        c.setEmail(email);
        persistencia.salvar(clientes);
        LoggerService.log("INFO", "Cliente alterado: ID " + id);
        return true;
    }

    // DELETE
    public boolean excluir(int id) {
        Cliente c = buscar(id);
        if (c == null) {
            return false;
        }
        clientes.remove(c);
        persistencia.salvar(clientes);
        LoggerService.log("INFO", "Cliente excluido: ID " + id);
        return true;
    }
}
