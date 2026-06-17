package view;

import java.util.Scanner;

public class Leitura {

    private final Scanner scanner;

    public Leitura(Scanner scanner) {
        this.scanner = scanner;
    }

    public String texto(String rotulo) {
        System.out.print(rotulo);
        return scanner.nextLine();
    }

    public int inteiro(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    public double decimal(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String entrada = scanner.nextLine().replace(",", ".");
            try {
                return Double.parseDouble(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numerico valido.");
            }
        }
    }

    public boolean simNao(String rotulo) {
        System.out.print(rotulo + " (s/n): ");
        String entrada = scanner.nextLine().trim().toLowerCase();
        return entrada.equals("s") || entrada.equals("sim");
    }
}
