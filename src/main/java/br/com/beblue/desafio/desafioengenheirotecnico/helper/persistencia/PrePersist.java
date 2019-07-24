package br.com.beblue.desafio.desafioengenheirotecnico.helper.persistencia;


import br.com.beblue.desafio.desafioengenheirotecnico.helper.mapeamento.AbsctractEntity;

import java.util.UUID;

public abstract class PrePersist<T extends AbsctractEntity> {

    public void prePersist(T entity) {
        entity.setId(UUID.randomUUID().toString());
    }
}
