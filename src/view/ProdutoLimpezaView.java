package view;

import java.util.List;
import java.util.Scanner;
import controller.CategoriaController;
import controller.FornecedorController;
import controller.ProdutoLimpezaController;
import model.Categoria;
import model.Fornecedor;
import model.ProdutoLimpeza;
import model.exceptions.ValidacaoException;

public class ProdutoLimpezaView {

    private final ProdutoLimpezaController controller;
    private final CategoriaController categoriaController;
    private final FornecedorController fornecedorController;
    private final Leitura leitura;

    public ProdutoLimpezaView(Scanner scanner, ProdutoLimpezaController controller,
                              CategoriaController categoriaController,
                              FornecedorController fornecedorController) {
        this.controller = controller;
        this.categoriaController = categoriaController;
        this.fornecedorController = fornecedorController;
        this.leitura = new Leitura(scanner);
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== PRODUTOS DE LIMPEZA ===");
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

    private Categoria selecionarCategoria() {
        List<Categoria> categorias = categoriaController.listar();
        if (categorias.isEmpty()) {
            System.out.println("(Nenhuma categoria cadastrada - o produto ficara sem categoria.)");
            return null;
        }
        System.out.println("Categorias disponiveis:");
        for (Categoria c : categorias) {
            System.out.println("  " + c);
        }
        int id = leitura.inteiro("ID da categoria (0 para nenhuma): ");
        return categoriaController.buscar(id);
    }

    private Fornecedor selecionarFornecedor() {
        List<Fornecedor> fornecedores = fornecedorController.listar();
        if (fornecedores.isEmpty()) {
            System.out.println("(Nenhum fornecedor cadastrado - o produto ficara sem fornecedor.)");
            return null;
        }
        System.out.println("Fornecedores disponiveis:");
        for (Fornecedor f : fornecedores) {
            System.out.println("  " + f);
        }
        int id = leitura.inteiro("ID do fornecedor (0 para nenhum): ");
        return fornecedorController.buscar(id);
    }

    private void cadastrar() {
        String nome = leitura.texto("Nome: ");
        double preco = leitura.decimal("Preco: ");
        int qtd = leitura.inteiro("Quantidade: ");
        Categoria categoria = selecionarCategoria();
        Fornecedor fornecedor = selecionarFornecedor();
        boolean toxico = leitura.simNao("Produto toxico?");
        try {
            ProdutoLimpeza p = controller.cadastrar(nome, preco, qtd, categoria, fornecedor, toxico);
            System.out.println("Produto cadastrado com ID " + p.getId() + ".");
        } catch (ValidacaoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listar() {
        List<ProdutoLimpeza> produtos = controller.listar();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (ProdutoLimpeza p : produtos) {
            System.out.println(p + " | toxico: " + (p.isToxico() ? "sim" : "nao"));
        }
    }

    private void alterar() {
        int id = leitura.inteiro("ID do produto: ");
        if (controller.buscar(id) == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }
        String nome = leitura.texto("Novo nome: ");
        double preco = leitura.decimal("Novo preco: ");
        int qtd = leitura.inteiro("Nova quantidade: ");
        Categoria categoria = selecionarCategoria();
        Fornecedor fornecedor = selecionarFornecedor();
        boolean toxico = leitura.simNao("Produto toxico?");
        try {
            boolean ok = controller.alterar(id, nome, preco, qtd, categoria, fornecedor, toxico);
            System.out.println(ok ? "Produto alterado." : "Falha ao alterar.");
        } catch (ValidacaoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void excluir() {
        int id = leitura.inteiro("ID do produto: ");
        boolean ok = controller.excluir(id);
        System.out.println(ok ? "Produto excluido." : "Produto nao encontrado.");
    }
}
