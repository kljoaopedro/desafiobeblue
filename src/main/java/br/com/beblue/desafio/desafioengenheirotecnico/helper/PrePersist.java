package br.com.beblue.desafio.desafioengenheirotecnico.helper;


import java.util.UUID;

public abstract class PrePersist<T extends AbsctractEntity> {

    public void prePersist(T entity) {
        entity.setId(UUID.randomUUID().toString());
    }
}
