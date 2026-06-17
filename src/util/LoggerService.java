package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Servico de Log do sistema.
 * Registra acoes e erros em um arquivo de texto (logs/log.txt).
 * Tipos usados: INFO (informacao), WARNING (alerta) e ERROR (erro).
 */
public class LoggerService {

    // Caminho relativo do arquivo de log
    private static final String ARQUIVO = "logs/log.txt";

    // Metodo principal para registrar uma linha no log
    public static void log(String tipo, String mensagem) {
        try {
            // Cria a pasta "logs" caso ela ainda nao exista
            File pasta = new File("logs");
            if (!pasta.exists()) {
                pasta.mkdir();
            }

            // Abre o arquivo em modo append (true = nao sobrescreve)
            FileWriter writer = new FileWriter(ARQUIVO, true);

            // Pega a data e hora atual
            LocalDateTime agora = LocalDateTime.now();

            // Monta a linha do log e escreve no arquivo
            String linha = "[" + agora + "] " + tipo + " - " + mensagem + "\n";
            writer.write(linha);
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao registrar log.");
        }
    }
}
