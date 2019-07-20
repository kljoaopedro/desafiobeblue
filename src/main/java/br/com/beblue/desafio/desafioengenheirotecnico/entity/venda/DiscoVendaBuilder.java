package br.com.beblue.desafio.desafioengenheirotecnico.entity.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;

public final class DiscoVendaBuilder {

    private String id;
    private Disco disco;
    private Venda venda;

    private DiscoVendaBuilder() {
    }

    public static DiscoVendaBuilder newInstance() {
        return new DiscoVendaBuilder();
    }

    public DiscoVendaBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public DiscoVendaBuilder withDisco(Disco disco) {
        this.disco = disco;
        return this;
    }

    public DiscoVendaBuilder withVenda(Venda venda) {
        this.venda = venda;
        return this;
    }

    public DiscoVenda build() {
        DiscoVenda discoVenda = new DiscoVenda();
        discoVenda.setId(id);
        discoVenda.setDisco(disco);
        discoVenda.setVenda(venda);
        return discoVenda;
    }
}
