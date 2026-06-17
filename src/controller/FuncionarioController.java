package controller;

import java.util.List;
import model.Funcionario;
import util.LoggerService;
import util.PersistenciaService;

/**
 * Controller do CRUD de Funcionarios.
 */
public class FuncionarioController {

    private List<Funcionario> funcionarios;
    private int proximoId;
    private final PersistenciaService<Funcionario> persistencia;

    public FuncionarioController() {
        persistencia = new PersistenciaService<Funcionario>("funcionarios.dat");
        funcionarios = persistencia.carregar();
        proximoId = calcularProximoId();
    }

    private int calcularProximoId() {
        int maior = 0;
        for (Funcionario f : funcionarios) {
            if (f.getId() > maior) {
                maior = f.getId();
            }
        }
        return maior + 1;
    }

    public Funcionario cadastrar(String nome, String cpf, String telefone, String cargo, double salario) {
        Funcionario f = new Funcionario(proximoId, nome, cpf, telefone, cargo, salario);
        funcionarios.add(f);
        proximoId++;
        persistencia.salvar(funcionarios);
        LoggerService.log("INFO", "Funcionario cadastrado: " + nome + " (ID " + f.getId() + ")");
        return f;
    }

    public List<Funcionario> listar() {
        return funcionarios;
    }

    public Funcionario buscar(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public boolean alterar(int id, String nome, String cpf, String telefone, String cargo, double salario) {
        Funcionario f = buscar(id);
        if (f == null) {
            return false;
        }
        f.setNome(nome);
        f.setCpf(cpf);
        f.setTelefone(telefone);
        f.setCargo(cargo);
        f.setSalario(salario);
        persistencia.salvar(funcionarios);
        LoggerService.log("INFO", "Funcionario alterado: ID " + id);
        return true;
    }

    public boolean excluir(int id) {
        Funcionario f = buscar(id);
        if (f == null) {
            return false;
        }
        funcionarios.remove(f);
        persistencia.salvar(funcionarios);
        LoggerService.log("INFO", "Funcionario excluido: ID " + id);
        return true;
    }
}
