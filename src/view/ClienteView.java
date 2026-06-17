package view;

import java.util.List;
import java.util.Scanner;
import controller.ClienteController;
import model.Cliente;

public class ClienteView {

    private final ClienteController controller;
    private final Leitura leitura;

    public ClienteView(Scanner scanner, ClienteController controller) {
        this.controller = controller;
        this.leitura = new Leitura(scanner);
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== CLIENTES ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            opcao = leitura.inteiro("Opcao: ");

            switch (opcao) {
                case 1:
                    cadastrar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    alterar();
                    break;
                case 4:
                    excluir();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private void cadastrar() {
        String nome = leitura.texto("Nome: ");
        String cpf = leitura.texto("CPF: ");
        String telefone = leitura.texto("Telefone: ");
        String email = leitura.texto("E-mail: ");
        Cliente c = controller.cadastrar(nome, cpf, telefone, email);
        System.out.println("Cliente cadastrado com ID " + c.getId() + ".");
    }

    private void listar() {
        List<Cliente> clientes = controller.listar();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println("[" + c.getId() + "] " + c.getNome()
                    + " | CPF: " + c.getCpf() + " | Tel: " + c.getTelefone()
                    + " | E-mail: " + c.getEmail());
        }
    }

    private void alterar() {
        int id = leitura.inteiro("ID do cliente: ");
        if (controller.buscar(id) == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }
        String nome = leitura.texto("Novo nome: ");
        String cpf = leitura.texto("Novo CPF: ");
        String telefone = leitura.texto("Novo telefone: ");
        String email = leitura.texto("Novo e-mail: ");
        boolean ok = controller.alterar(id, nome, cpf, telefone, email);
        System.out.println(ok ? "Cliente alterado." : "Falha ao alterar.");
    }

    private void excluir() {
        int id = leitura.inteiro("ID do cliente: ");
        boolean ok = controller.excluir(id);
        System.out.println(ok ? "Cliente excluido." : "Cliente nao encontrado.");
    }
}
