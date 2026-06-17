package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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
