package br.com.beblue.desafio.desafioengenheirotecnico.entity.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;

import java.io.Serializable;


/**
 * Pojo que faz a comunicção com a API.
 */
public class VendaMv implements Serializable {


    /**
     * Quantidade de discos.
     */
    private Integer quantidade;

    /**
     * Genero do Disco.
     */
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
