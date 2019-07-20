package br.com.beblue.desafio.desafioengenheirotecnico.entity.venda;

import java.math.BigDecimal;
import java.util.List;

public final class VendaBuilder {
    private String id;
    private List<DiscoVenda> discoVenda;
    private BigDecimal valorTotal;
    private BigDecimal totalCashBack;
    private Integer totalItens;

    private VendaBuilder() {
    }

    public static VendaBuilder newInstance() {
        return new VendaBuilder();
    }

    public VendaBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public VendaBuilder withDiscosVenda(List<DiscoVenda> discosVenda) {
        this.discoVenda = discosVenda;
        return this;
    }

    public VendaBuilder withValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public VendaBuilder withTotalCashBack(BigDecimal totalCashBack) {
        this.totalCashBack = totalCashBack;
        return this;
    }

    public VendaBuilder withTotalItens(Integer totalItens) {
        this.totalItens = totalItens;
        return this;
    }

    public Venda build() {
        Venda venda = new Venda();
        venda.setId(id);
        venda.setDiscos(discoVenda);
        venda.setValorTotal(valorTotal);
        venda.setTotalCashBack(totalCashBack);
        venda.setTotalItens(totalItens);
        return venda;
    }
}
