import java.util.Scanner;

import controller.CategoriaController;
import controller.ClienteController;
import controller.FornecedorController;
import controller.FuncionarioController;
import controller.PedidoController;
import controller.ProdutoAlimenticioController;
import controller.ProdutoLimpezaController;
import controller.ProdutoVestuarioController;
import util.LoggerService;
import view.CategoriaView;
import view.ClienteView;
import view.FornecedorView;
import view.FuncionarioView;
import view.PedidoView;
import view.ProdutoAlimenticioView;
import view.ProdutoLimpezaView;
import view.ProdutoVestuarioView;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoggerService.log("INFO", "Sistema iniciado");

        ClienteController clienteController = new ClienteController();
        FuncionarioController funcionarioController = new FuncionarioController();
        CategoriaController categoriaController = new CategoriaController();
        FornecedorController fornecedorController = new FornecedorController();
        ProdutoAlimenticioController alimenticioController = new ProdutoAlimenticioController();
        ProdutoLimpezaController limpezaController = new ProdutoLimpezaController();
        ProdutoVestuarioController vestuarioController = new ProdutoVestuarioController();
        PedidoController pedidoController = new PedidoController();

        ClienteView clienteView = new ClienteView(scanner, clienteController);
        FuncionarioView funcionarioView = new FuncionarioView(scanner, funcionarioController);
        CategoriaView categoriaView = new CategoriaView(scanner, categoriaController);
        FornecedorView fornecedorView = new FornecedorView(scanner, fornecedorController);
        ProdutoAlimenticioView alimenticioView = new ProdutoAlimenticioView(scanner,
                alimenticioController, categoriaController, fornecedorController);
        ProdutoLimpezaView limpezaView = new ProdutoLimpezaView(scanner,
                limpezaController, categoriaController, fornecedorController);
        ProdutoVestuarioView vestuarioView = new ProdutoVestuarioView(scanner,
                vestuarioController, categoriaController, fornecedorController);
        PedidoView pedidoView = new PedidoView(scanner, pedidoController, clienteController,
                alimenticioController, limpezaController, vestuarioController);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n========================================");
            System.out.println("        SISTEMA DE MERCADO");
            System.out.println("========================================");
            System.out.println("1 - Clientes");
            System.out.println("2 - Funcionarios");
            System.out.println("3 - Categorias");
            System.out.println("4 - Fornecedores");
            System.out.println("5 - Produtos Alimenticios");
            System.out.println("6 - Produtos de Limpeza");
            System.out.println("7 - Produtos de Vestuario");
            System.out.println("8 - Pedidos");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            String entrada = scanner.nextLine();
            try {
                opcao = Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
                continue;
            }

            switch (opcao) {
                case 1: clienteView.exibirMenu(); break;
                case 2: funcionarioView.exibirMenu(); break;
                case 3: categoriaView.exibirMenu(); break;
                case 4: fornecedorView.exibirMenu(); break;
                case 5: alimenticioView.exibirMenu(); break;
                case 6: limpezaView.exibirMenu(); break;
                case 7: vestuarioView.exibirMenu(); break;
                case 8: pedidoView.exibirMenu(); break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    LoggerService.log("INFO", "Sistema encerrado");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }

        scanner.close();
    }
}
