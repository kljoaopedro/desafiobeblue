package br.com.beblue.desafio.desafioengenheirotecnico.entity.disco;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.List;


public class Catalogo implements Serializable {


    private List<Disco> discos;

    public Catalogo() {
    }


    public List<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<Disco> discos) {
        this.discos = discos;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("discos", discos)
                .toString();
    }
}
