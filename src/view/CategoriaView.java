package view;

import java.util.List;
import java.util.Scanner;
import controller.CategoriaController;
import model.Categoria;

public class CategoriaView {

    private final CategoriaController controller;
    private final Leitura leitura;

    public CategoriaView(Scanner scanner, CategoriaController controller) {
        this.controller = controller;
        this.leitura = new Leitura(scanner);
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== CATEGORIAS ===");
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
        String nome = leitura.texto("Nome da categoria: ");
        Categoria c = controller.cadastrar(nome);
        System.out.println("Categoria cadastrada com ID " + c.getId() + ".");
    }

    private void listar() {
        List<Categoria> categorias = controller.listar();
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
            return;
        }
        for (Categoria c : categorias) {
            System.out.println(c);
        }
    }

    private void alterar() {
        int id = leitura.inteiro("ID da categoria: ");
        if (controller.buscar(id) == null) {
            System.out.println("Categoria nao encontrada.");
            return;
        }
        String nome = leitura.texto("Novo nome: ");
        boolean ok = controller.alterar(id, nome);
        System.out.println(ok ? "Categoria alterada." : "Falha ao alterar.");
    }

    private void excluir() {
        int id = leitura.inteiro("ID da categoria: ");
        boolean ok = controller.excluir(id);
        System.out.println(ok ? "Categoria excluida." : "Categoria nao encontrada.");
    }
}
