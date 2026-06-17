package controller;

import java.util.List;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.Produto;
import util.LoggerService;
import util.PersistenciaService;


public class PedidoController {

    private List<Pedido> pedidos;
    private int proximoId;
    private final PersistenciaService<Pedido> persistencia;

    public PedidoController() {
        persistencia = new PersistenciaService<Pedido>("pedidos.dat");
        pedidos = persistencia.carregar();
        proximoId = calcularProximoId();
    }

    private int calcularProximoId() {
        int maior = 0;
        for (Pedido p : pedidos) {
            if (p.getId() > maior) {
                maior = p.getId();
            }
        }
        return maior + 1;
    }

    public Pedido criar(Cliente cliente) {
        Pedido pedido = new Pedido(proximoId, cliente);
        pedidos.add(pedido);
        proximoId++;
        LoggerService.log("INFO", "Pedido criado para o cliente: " + cliente.getNome());
        return pedido;
    }

    // SOBRECARGA 1: adiciona um item informando a quantidade
    public void adicionarItem(Pedido pedido, Produto produto, int quantidade) {
        pedido.adicionarItem(new ItemPedido(produto, quantidade));
    }

    // SOBRECARGA 2: adiciona um item assumindo quantidade 1 (mesmo nome, assinatura diferente)
    public void adicionarItem(Pedido pedido, Produto produto) {
        adicionarItem(pedido, produto, 1);
    }

    public void finalizar(Pedido pedido) {
        persistencia.salvar(pedidos);
        LoggerService.log("INFO", "Pedido finalizado: ID " + pedido.getId()
                + " | total R$ " + String.format("%.2f", pedido.getTotal()));
    }

    public List<Pedido> listar() {
        return pedidos;
    }

    public Pedido buscar(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean excluir(int id) {
        Pedido p = buscar(id);
        if (p == null) {
            return false;
        }
        pedidos.remove(p);
        persistencia.salvar(pedidos);
        LoggerService.log("INFO", "Pedido excluido: ID " + id);
        return true;
    }
}
