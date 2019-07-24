package br.com.beblue.desafio.desafioengenheirotecnico.helper;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class PrePersistTest {

    @Test
    public void uuid() {
        String uuid = UUID.randomUUID().toString();
        assertEquals(36, uuid.length());
    }

}