package br.com.beblue.desafio.desafioengenheirotecnico.entity.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.helper.AbsctractEntity;
import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Disco extends AbsctractEntity {

    public static final String ID_ATRIBUTTE_NAME = "id";
    public static final String DISCO_ID_COLNAME = "DISCO_ID";

    /**
     * Identificador.
     */
    @Id
    private String id;

    /**
     * Genero do repository.
     */
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;


    /**
     * Preço do repository
     */
    private BigDecimal valor;

    /**
     * Valor do cashBack
     */
    private BigDecimal cashBack;

    private BigDecimal porcentagemCashBack;

    public Disco() {
        //Construtor padrão
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getCashBack() {
        return cashBack;
    }

    public void setCashBack(BigDecimal cashBack) {
        this.cashBack = cashBack;
    }

    public BigDecimal getPorcentagemCashBack() {
        return porcentagemCashBack;
    }

    public void setPorcentagemCashBack(BigDecimal porcentagemCashBack) {
        this.porcentagemCashBack = porcentagemCashBack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disco disco = (Disco) o;
        return Objects.equal(id, disco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("genero", genero)
                .add("valor", valor)
                .add("cashBack", cashBack)
                .add("porcentagemCashBack", porcentagemCashBack)
                .toString();
    }
}
