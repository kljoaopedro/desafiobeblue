package br.com.beblue.desafio.desafioengenheirotecnico.pojo.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;

/**
 * Objeto que ajuda na construção de um VendaMv.
 */
public final class VendaMvBuilder {
    private Integer quantidade;
    private GeneroEnum generoEnum;

    private VendaMvBuilder() {
    }

    public static VendaMvBuilder newInstance() {
        return new VendaMvBuilder();
    }

    public VendaMvBuilder withQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public VendaMvBuilder withGeneroEnum(GeneroEnum generoEnum) {
        this.generoEnum = generoEnum;
        return this;
    }

    public VendaMv build() {
        VendaMv vendaMv = new VendaMv();
        vendaMv.setQuantidade(quantidade);
        vendaMv.setGeneroEnum(generoEnum);
        return vendaMv;
    }
}
