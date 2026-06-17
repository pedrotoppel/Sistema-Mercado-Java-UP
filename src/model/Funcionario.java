package model;

public class Funcionario extends Pessoa {

    private static final long serialVersionUID = 1L;

    private String cargo;
    private double salario;

    public Funcionario(int id, String nome, String cpf, String telefone, String cargo, double salario) {
        super(id, nome, cpf, telefone);
        this.cargo = cargo;
        this.salario = salario;
    }

    // POLIMORFISMO DE SOBRESCRITA
    @Override
    public String getTipo() {
        return "Funcionario";
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
