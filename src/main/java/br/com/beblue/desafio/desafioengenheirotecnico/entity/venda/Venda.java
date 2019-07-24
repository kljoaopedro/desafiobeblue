package br.com.beblue.desafio.desafioengenheirotecnico.entity.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.helper.mapeamento.AbsctractEntity;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Venda extends AbsctractEntity {

    public static final String VENDA_ID_ATRIBUTTE_NAME = "id";
    public static final String VENDA_ID_COLNAME = "VENDA_ID";
    private static final String DATA_VENDA_COLNAME = "VENDA_DATA";

    @OneToMany(mappedBy = AbstractVenda.VENDA_ATRIBUTTE_NAME, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<DiscoVenda> discos;
    @Id
    @Column(name = VENDA_ID_COLNAME)
    private String id;
    private BigDecimal valorTotal;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = DATA_VENDA_COLNAME)
    private Date dataVenda;

    private BigDecimal totalCashBack;

    private Integer totalItens;

    public Venda() {
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

    public List<DiscoVenda> getDiscos() {
        return discos;
    }

    public void setDiscos(List<DiscoVenda> discos) {
        this.discos = discos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getTotalCashBack() {
        return totalCashBack;
    }

    public void setTotalCashBack(BigDecimal totalCashBack) {
        this.totalCashBack = totalCashBack;
    }

    public Integer getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(Integer totalItens) {
        this.totalItens = totalItens;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equal(id, venda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .toString();
    }
}
