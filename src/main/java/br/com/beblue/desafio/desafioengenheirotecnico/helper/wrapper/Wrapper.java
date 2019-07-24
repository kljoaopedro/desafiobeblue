package br.com.beblue.desafio.desafioengenheirotecnico.helper.wrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Objeto que auxilia nas respostas da API.
 *
 * @param <T>
 */
public class Wrapper<T> {

    /**
     * Status do retorno.
     */
    private int status;

    /**
     * Mensagem de erro caso houver.
     */
    private String error;

    /**
     * Conteudo da API.
     */
    private T body;

    public Wrapper() {
        this(200);
    }

    public Wrapper(final int status, final T body) {
        this.status = status;
        this.body = body;
    }

    public Wrapper(final int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public T getBody() {
        return body;
    }

    public void setBody(final T data) {
        this.body = data;
    }


    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
