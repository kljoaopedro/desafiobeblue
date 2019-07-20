package br.com.beblue.desafio.desafioengenheirotecnico.entity.venda;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass
public abstract class AbstractVenda implements Serializable {

    public static final String VENDA_ATRIBUTTE_NAME = "venda";


    @ManyToOne(targetEntity = Venda.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Venda.VENDA_ID_COLNAME)
    private Venda venda;

    public AbstractVenda() {
        //Construtor padrão
    }

    @JsonIgnore
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractVenda that = (AbstractVenda) o;
        return Objects.equal(venda, that.venda);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(venda);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("venda", venda)
                .toString();
    }
}
