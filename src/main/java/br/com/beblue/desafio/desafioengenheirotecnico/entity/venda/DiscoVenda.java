package br.com.beblue.desafio.desafioengenheirotecnico.entity.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe responsavel pela relação entre Disco e Venda.
 */
@Entity
public class DiscoVenda extends AbstractVenda implements Serializable {


    /**
     * Identificador(PK).
     */
    @Id
    @Column(name = "DISCO_VENDA_ID")
    private String id;

    /**
     * Disco.
     */
    @ManyToOne(targetEntity = Disco.class, fetch = FetchType.LAZY)
    @JoinColumn(name = Disco.DISCO_ID_COLNAME)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Disco disco;

    public DiscoVenda() {
        //Construtor padrão
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscoVenda that = (DiscoVenda) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("disco", disco)
                .toString();
    }
}
