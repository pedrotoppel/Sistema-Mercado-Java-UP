package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Servico de Persistencia generico usando Serializacao.
 * Salva e recupera listas de objetos em arquivos .dat dentro da pasta "dados".
 * Usa Generics (<T>) para funcionar com qualquer objeto Serializable do sistema.
 */
public class PersistenciaService<T> {

    private final String caminhoArquivo;

    public PersistenciaService(String nomeArquivo) {
        this.caminhoArquivo = "dados/" + nomeArquivo;
        criarPastaDados();
    }

    private void criarPastaDados() {
        File pasta = new File("dados");
        if (!pasta.exists()) {
            pasta.mkdir();
        }
    }

    // Salva a lista de objetos (Objeto -> Arquivo)
    public void salvar(List<T> lista) {
        try {
            FileOutputStream arquivo = new FileOutputStream(caminhoArquivo);
            ObjectOutputStream out = new ObjectOutputStream(arquivo);
            out.writeObject(lista);
            out.close();
        } catch (IOException e) {
            LoggerService.log("ERROR", "Erro ao salvar " + caminhoArquivo + ": " + e.getMessage());
        }
    }

    // Recupera a lista de objetos (Arquivo -> Objeto)
    @SuppressWarnings("unchecked")
    public List<T> carregar() {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return new ArrayList<T>();
        }
        try {
            FileInputStream file = new FileInputStream(caminhoArquivo);
            ObjectInputStream in = new ObjectInputStream(file);
            List<T> lista = (List<T>) in.readObject();
            in.close();
            return lista;
        } catch (IOException | ClassNotFoundException e) {
            LoggerService.log("ERROR", "Erro ao carregar " + caminhoArquivo + ": " + e.getMessage());
            return new ArrayList<T>();
        }
    }
}
