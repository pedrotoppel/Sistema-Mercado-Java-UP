package view;

import java.util.List;
import java.util.Scanner;
import controller.FuncionarioController;
import model.Funcionario;

public class FuncionarioView {

    private final FuncionarioController controller;
    private final Leitura leitura;

    public FuncionarioView(Scanner scanner, FuncionarioController controller) {
        this.controller = controller;
        this.leitura = new Leitura(scanner);
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== FUNCIONARIOS ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            opcao = leitura.inteiro("Opcao: ");

            switch (opcao) {
                case 1: cadastrar(); break;
                case 2: listar(); break;
                case 3: alterar(); break;
                case 4: excluir(); break;
                case 0: break;
                default: System.out.println("Opcao invalida.");
            }
        }
    }

    private void cadastrar() {
        String nome = leitura.texto("Nome: ");
        String cpf = leitura.texto("CPF: ");
        String telefone = leitura.texto("Telefone: ");
        String cargo = leitura.texto("Cargo: ");
        double salario = leitura.decimal("Salario: ");
        Funcionario f = controller.cadastrar(nome, cpf, telefone, cargo, salario);
        System.out.println("Funcionario cadastrado com ID " + f.getId() + ".");
    }

    private void listar() {
        List<Funcionario> funcionarios = controller.listar();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            System.out.println("[" + f.getId() + "] " + f.getNome()
                    + " | Cargo: " + f.getCargo()
                    + " | Salario: R$ " + String.format("%.2f", f.getSalario()));
        }
    }

    private void alterar() {
        int id = leitura.inteiro("ID do funcionario: ");
        if (controller.buscar(id) == null) {
            System.out.println("Funcionario nao encontrado.");
            return;
        }
        String nome = leitura.texto("Novo nome: ");
        String cpf = leitura.texto("Novo CPF: ");
        String telefone = leitura.texto("Novo telefone: ");
        String cargo = leitura.texto("Novo cargo: ");
        double salario = leitura.decimal("Novo salario: ");
        boolean ok = controller.alterar(id, nome, cpf, telefone, cargo, salario);
        System.out.println(ok ? "Funcionario alterado." : "Falha ao alterar.");
    }

    private void excluir() {
        int id = leitura.inteiro("ID do funcionario: ");
        boolean ok = controller.excluir(id);
        System.out.println(ok ? "Funcionario excluido." : "Funcionario nao encontrado.");
    }
}
