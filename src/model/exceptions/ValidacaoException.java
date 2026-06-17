package model.exceptions;

/**
 * Excecao personalizada para erros de validacao de dados
 * (ex.: preco negativo, quantidade invalida).
 * Usada no TRATAMENTO DE EXCECOES do sistema.
 */
public class ValidacaoException extends Exception {
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
