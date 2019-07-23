package br.com.beblue.desafio.desafioengenheirotecnico.entity.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.helper.AbsctractEntity;
import br.com.beblue.desafio.desafioengenheirotecnico.helper.LengthColumn;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Entidade que representa o Disco.
 */
@Entity(name = Disco.TABLE_NAME)
public class Disco extends AbsctractEntity {

    public static final String TABLE_NAME = "DISCO";
    public static final String ID_ATRIBUTTE_NAME = "id";
    public static final String DISCO_ID_COLNAME = "DISCO_ID";
    private static final String NOME_COLNAME = "DISCO_NOME";
    private static final String GENERO_COLNAME = "DISCO_GENERO";
    private static final String VALOR_COLNAME = "DISCO_VALOR";
    private static final String VALOR_CASH_COLNAME = "DISCO_VALOR_CASH_BACK";
    private static final String PORCENTAGEM_COLNAME = "DISCO_PORCENTAGEM_CASH_BACK";

    /**
     * Identificador(PK).
     */
    @Id
    @Column(name = DISCO_ID_COLNAME, length = LengthColumn.ID)
    private String id;

    /**
     * Nome do disco.
     */
    @Column(name = NOME_COLNAME, length = LengthColumn.TEXT)
    private String nome;

    /**
     * Genero do disco.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = GENERO_COLNAME, length = LengthColumn.TEXT)
    private GeneroEnum genero;


    /**
     * Valor do disco sem o cashBack.
     */
    @Column(name = VALOR_COLNAME, columnDefinition = LengthColumn.DECIMAL)
    private BigDecimal valor;

    /**
     * Valor do valorCashBack
     */
    @Column(name = VALOR_CASH_COLNAME, columnDefinition = LengthColumn.DECIMAL)
    private BigDecimal valorCashBack;

    /**
     * Porcentagem do cashBack dependendo dia da semana.
     */
    @Column(name = PORCENTAGEM_COLNAME, columnDefinition = LengthColumn.DECIMAL)
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public BigDecimal getValorCashBack() {
        return valorCashBack;
    }

    public void setValorCashBack(BigDecimal valorCashBack) {
        this.valorCashBack = valorCashBack;
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
                .add("valorCashBack", valorCashBack)
                .add("porcentagemCashBack", porcentagemCashBack)
                .toString();
    }
}
