package model;

/**
 * Subclasse de Pessoa (Heranca).
 */
public class Cliente extends Pessoa {

    private static final long serialVersionUID = 1L;

    private String email;

    public Cliente(int id, String nome, String cpf, String telefone, String email) {
        super(id, nome, cpf, telefone); // chama o construtor da superclasse
        this.email = email;
    }

    // POLIMORFISMO DE SOBRESCRITA (@Override)
    @Override
    public String getTipo() {
        return "Cliente";
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
