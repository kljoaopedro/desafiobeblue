package br.com.beblue.desafio.desafioengenheirotecnico.pojo.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;

import java.util.List;

public final class PageDiscoBuilder {
    private List<Disco> discos;
    private long totalElements;
    private int totalPages;

    private PageDiscoBuilder() {
    }

    public static PageDiscoBuilder newInstance() {
        return new PageDiscoBuilder();
    }

    public PageDiscoBuilder withDiscos(List<Disco> discos) {
        this.discos = discos;
        return this;
    }

    public PageDiscoBuilder withTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public PageDiscoBuilder withTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public PageDisco build() {
        PageDisco pageDisco = new PageDisco();
        pageDisco.setDiscos(discos);
        pageDisco.setTotalElements(totalElements);
        pageDisco.setTotalPages(totalPages);
        return pageDisco;
    }
}
