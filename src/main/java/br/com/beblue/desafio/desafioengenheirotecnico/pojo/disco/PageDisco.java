package br.com.beblue.desafio.desafioengenheirotecnico.pojo.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;

import java.io.Serializable;
import java.util.List;

/**
 * Model View de resposta para API de paginação de Disco.
 */
public class PageDisco implements Serializable {

    private List<Disco> discos;

    private long totalElements;

    private int totalPages;

    public PageDisco() {
        //Construtor padrão
    }

    public List<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<Disco> discos) {
        this.discos = discos;
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
