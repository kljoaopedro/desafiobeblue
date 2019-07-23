package br.com.beblue.desafio.desafioengenheirotecnico.exception;

public class LoadDataException extends Throwable {

    public LoadDataException(String message) {
        super(message);
    }

    public LoadDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
