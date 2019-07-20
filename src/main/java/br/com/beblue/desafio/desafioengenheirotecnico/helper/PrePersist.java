package br.com.beblue.desafio.desafioengenheirotecnico.helper;


import org.apache.commons.lang3.RandomStringUtils;

public abstract class PrePersist<T extends AbsctractEntity> {

    public void prePersist(T entity) {
        entity.setId(RandomStringUtils.randomAlphanumeric(40));
    }
}
