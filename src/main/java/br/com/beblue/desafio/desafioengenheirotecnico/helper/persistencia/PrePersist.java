package br.com.beblue.desafio.desafioengenheirotecnico.helper.persistencia;


import br.com.beblue.desafio.desafioengenheirotecnico.helper.mapeamento.AbsctractEntity;

import java.util.UUID;

/**
 * Classe que vai inserir um ID randomicamente.
 *
 * @param <T> Entidade.
 */
public abstract class PrePersist<T extends AbsctractEntity> {

    /**
     * Insere um UUID.
     *
     * @param entity Entidade.
     */
    public void prePersist(T entity) {
        entity.setId(UUID.randomUUID().toString());
    }
}
