package view;

import java.util.List;
import java.util.Scanner;
import controller.FornecedorController;
import model.Fornecedor;

public class FornecedorView {

    private final FornecedorController controller;
    private final Leitura leitura;

    public FornecedorView(Scanner scanner, FornecedorController controller) {
        this.controller = controller;
        this.leitura = new Leitura(scanner);
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== FORNECEDORES ===");
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
        String nome = leitura.texto("Nome do fornecedor: ");
        String cnpj = leitura.texto("CNPJ: ");
        Fornecedor f = controller.cadastrar(nome, cnpj);
        System.out.println("Fornecedor cadastrado com ID " + f.getId() + ".");
    }

    private void listar() {
        List<Fornecedor> fornecedores = controller.listar();
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
            return;
        }
        for (Fornecedor f : fornecedores) {
            System.out.println(f);
        }
    }

    private void alterar() {
        int id = leitura.inteiro("ID do fornecedor: ");
        if (controller.buscar(id) == null) {
            System.out.println("Fornecedor nao encontrado.");
            return;
        }
        String nome = leitura.texto("Novo nome: ");
        String cnpj = leitura.texto("Novo CNPJ: ");
        boolean ok = controller.alterar(id, nome, cnpj);
        System.out.println(ok ? "Fornecedor alterado." : "Falha ao alterar.");
    }

    private void excluir() {
        int id = leitura.inteiro("ID do fornecedor: ");
        boolean ok = controller.excluir(id);
        System.out.println(ok ? "Fornecedor excluido." : "Fornecedor nao encontrado.");
    }
}
