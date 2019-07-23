package br.com.beblue.desafio.desafioengenheirotecnico.entity.disco;

import java.io.Serializable;


public class VendaMv implements Serializable {


    private Integer quantidade;

    private GeneroEnum generoEnum;

    public VendaMv() {
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public GeneroEnum getGeneroEnum() {
        return generoEnum;
    }

    public void setGeneroEnum(GeneroEnum generoEnum) {
        this.generoEnum = generoEnum;
    }


}
