package br.com.beblue.desafio.desafioengenheirotecnico.exception;

/**
 * Exceção que é lançada quando não se carrega o banco de dados.
 */
public class LoadDataException extends Throwable {

    public LoadDataException(String message) {
        super(message);
    }

    public LoadDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
