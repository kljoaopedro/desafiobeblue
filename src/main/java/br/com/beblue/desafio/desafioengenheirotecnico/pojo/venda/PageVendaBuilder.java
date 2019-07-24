package br.com.beblue.desafio.desafioengenheirotecnico.pojo.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.Venda;

import java.util.List;

public final class PageVendaBuilder {
    private List<Venda> vendas;
    private long totalElements;
    private int totalPages;

    private PageVendaBuilder() {
    }

    public static PageVendaBuilder newInstance() {
        return new PageVendaBuilder();
    }

    public PageVendaBuilder withVendas(List<Venda> vendas) {
        this.vendas = vendas;
        return this;
    }

    public PageVendaBuilder withTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public PageVendaBuilder withTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public PageVenda build() {
        PageVenda pageVenda = new PageVenda();
        pageVenda.setVendas(vendas);
        pageVenda.setTotalElements(totalElements);
        pageVenda.setTotalPages(totalPages);
        return pageVenda;
    }
}
