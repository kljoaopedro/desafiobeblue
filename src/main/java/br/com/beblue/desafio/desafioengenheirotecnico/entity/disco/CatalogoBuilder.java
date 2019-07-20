package br.com.beblue.desafio.desafioengenheirotecnico.entity.disco;

import java.util.List;

public final class CatalogoBuilder {
    private List<Disco> discos;

    private CatalogoBuilder() {
    }

    public static CatalogoBuilder newInstance() {
        return new CatalogoBuilder();
    }

    public CatalogoBuilder withDiscos(List<Disco> discos) {
        this.discos = discos;
        return this;
    }

    public Catalogo build() {
        Catalogo catalogo = new Catalogo();
        catalogo.setDiscos(discos);
        return catalogo;
    }
}
