package br.com.beblue.desafio.desafioengenheirotecnico.helper.wrapper;

public final class WrapperBuilder<T> {
    private String error;
    private int status;
    private T body;

    private WrapperBuilder() {
    }

    public static WrapperBuilder newInstance() {
        return new WrapperBuilder();
    }

    public WrapperBuilder withError(String error) {
        this.error = error;
        return this;
    }

    public WrapperBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public WrapperBuilder withBody(T body) {
        this.body = body;
        return this;
    }

    public Wrapper build() {
        Wrapper wrapper = new Wrapper(status, body);
        wrapper.setError(error);
        return wrapper;
    }
}
