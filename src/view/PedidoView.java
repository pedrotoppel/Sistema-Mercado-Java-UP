package view;

import java.util.List;
import java.util.Scanner;
import controller.ClienteController;
import controller.PedidoController;
import controller.ProdutoAlimenticioController;
import controller.ProdutoLimpezaController;
import controller.ProdutoVestuarioController;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

/**
 * View de Pedidos.
 * Junta um Cliente (associacao) com varios Produtos (polimorfismo:
 * qualquer subclasse de Produto e tratada como Produto).
 */
public class PedidoView {

    private final PedidoController controller;
    private final ClienteController clienteController;
    private final ProdutoAlimenticioController alimenticioController;
    private final ProdutoLimpezaController limpezaController;
    private final ProdutoVestuarioController vestuarioController;
    private final Leitura leitura;

    public PedidoView(Scanner scanner, PedidoController controller,
                      ClienteController clienteController,
                      ProdutoAlimenticioController alimenticioController,
                      ProdutoLimpezaController limpezaController,
                      ProdutoVestuarioController vestuarioController) {
        this.controller = controller;
        this.clienteController = clienteController;
        this.alimenticioController = alimenticioController;
        this.limpezaController = limpezaController;
        this.vestuarioController = vestuarioController;
        this.leitura = new Leitura(scanner);
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== PEDIDOS ===");
            System.out.println("1 - Novo pedido");
            System.out.println("2 - Listar pedidos");
            System.out.println("3 - Excluir pedido");
            System.out.println("0 - Voltar");
            opcao = leitura.inteiro("Opcao: ");

            switch (opcao) {
                case 1: novoPedido(); break;
                case 2: listar(); break;
                case 3: excluir(); break;
                case 0: break;
                default: System.out.println("Opcao invalida.");
            }
        }
    }

    private void novoPedido() {
        List<Cliente> clientes = clienteController.listar();
        if (clientes.isEmpty()) {
            System.out.println("Cadastre um cliente antes de criar um pedido.");
            return;
        }
        System.out.println("Clientes:");
        for (Cliente c : clientes) {
            System.out.println("  [" + c.getId() + "] " + c.getNome());
        }
        int idCliente = leitura.inteiro("ID do cliente: ");
        Cliente cliente = clienteController.buscar(idCliente);
        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        Pedido pedido = controller.criar(cliente);

        boolean continuar = true;
        while (continuar) {
            Produto produto = selecionarProduto();
            if (produto != null) {
                int qtd = leitura.inteiro("Quantidade: ");
                // POLIMORFISMO: produto pode ser qualquer subclasse de Produto
                controller.adicionarItem(pedido, produto, qtd);
                System.out.println("Item adicionado.");
            }
            continuar = leitura.simNao("Adicionar outro item?");
        }

        if (pedido.getItens().isEmpty()) {
            System.out.println("Pedido sem itens. Cancelado.");
            controller.excluir(pedido.getId());
            return;
        }

        controller.finalizar(pedido);
        System.out.println("Pedido " + pedido.getId() + " finalizado. Total: R$ "
                + String.format("%.2f", pedido.getTotal()));
    }

    private Produto selecionarProduto() {
        System.out.println("Tipo de produto: 1-Alimenticio  2-Limpeza  3-Vestuario");
        int tipo = leitura.inteiro("Tipo: ");
        List<? extends Produto> lista;
        if (tipo == 1) {
            lista = alimenticioController.listar();
        } else if (tipo == 2) {
            lista = limpezaController.listar();
        } else if (tipo == 3) {
            lista = vestuarioController.listar();
        } else {
            System.out.println("Tipo invalido.");
            return null;
        }

        if (lista.isEmpty()) {
            System.out.println("Nenhum produto desse tipo cadastrado.");
            return null;
        }
        for (Produto p : lista) {
            System.out.println("  [" + p.getId() + "] " + p.getNome()
                    + " - R$ " + String.format("%.2f", p.getPrecoFinal()));
        }
        int id = leitura.inteiro("ID do produto: ");
        for (Produto p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        System.out.println("Produto nao encontrado.");
        return null;
    }

    private void listar() {
        List<Pedido> pedidos = controller.listar();
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }
        for (Pedido p : pedidos) {
            System.out.println("\nPedido [" + p.getId() + "] - Cliente: " + p.getCliente().getNome()
                    + " - Data: " + p.getData());
            for (ItemPedido item : p.getItens()) {
                System.out.println("   " + item.getQuantidade() + "x " + item.getProduto().getNome()
                        + " = R$ " + String.format("%.2f", item.getSubtotal()));
            }
            System.out.println("   TOTAL: R$ " + String.format("%.2f", p.getTotal()));
        }
    }

    private void excluir() {
        int id = leitura.inteiro("ID do pedido: ");
        boolean ok = controller.excluir(id);
        System.out.println(ok ? "Pedido excluido." : "Pedido nao encontrado.");
    }
}
