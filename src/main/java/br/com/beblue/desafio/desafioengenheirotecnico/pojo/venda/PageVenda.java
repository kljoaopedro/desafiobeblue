package br.com.beblue.desafio.desafioengenheirotecnico.pojo.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.Venda;

import java.io.Serializable;
import java.util.List;

/**
 * Pojo que representa o retorno da API de busca paginada de Venda.
 */
public class PageVenda implements Serializable {

    private List<Venda> vendas;
    private long totalElements;
    private int totalPages;

    public PageVenda() {
        //Construtor padrão
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
