package br.com.beblue.desafio.desafioengenheirotecnico.helper;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Classe que toda entidade deve herdar.
 */
@MappedSuperclass
public abstract class AbsctractEntity implements Serializable {


    public AbsctractEntity() {
    }

    /**
     * Retorna a chave primária do models.
     *
     * @return PK do model
     */
    public abstract String getId();

    /**
     * Define a chave primária do models.
     *
     * @param id Cahve primária
     */
    public abstract void setId(final String id);
}
