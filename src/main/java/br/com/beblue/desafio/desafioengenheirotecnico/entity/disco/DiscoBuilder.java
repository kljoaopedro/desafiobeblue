package br.com.beblue.desafio.desafioengenheirotecnico.entity.disco;

import java.math.BigDecimal;

public final class DiscoBuilder {

    private String id;
    private String nome;
    private GeneroEnum genero;
    private BigDecimal valor;
    private BigDecimal cashBack;
    private BigDecimal porcentagemCashBack;

    private DiscoBuilder() {
    }

    public static DiscoBuilder newInstance() {
        return new DiscoBuilder();
    }

    public DiscoBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public DiscoBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    }

    public DiscoBuilder withGenero(GeneroEnum genero) {
        this.genero = genero;
        return this;
    }

    public DiscoBuilder withValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public DiscoBuilder withCashBack(BigDecimal cashBack) {
        this.cashBack = cashBack;
        return this;
    }

    public DiscoBuilder withPorcentagemCashBack(BigDecimal porcentagemCashBack) {
        this.porcentagemCashBack = porcentagemCashBack;
        return this;
    }

    public Disco build() {
        Disco disco = new Disco();
        disco.setId(id);
        disco.setNome(nome);
        disco.setGenero(genero);
        disco.setValor(valor);
        disco.setCashBack(cashBack);
        disco.setPorcentagemCashBack(porcentagemCashBack);
        return disco;
    }
}
