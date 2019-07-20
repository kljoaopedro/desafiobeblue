package br.com.beblue.desafio.desafioengenheirotecnico.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrePersistTest {

    @Test
    public void ramAlpha() {
        String numeric = RandomStringUtils.randomAlphanumeric(40);
        assertEquals(40, numeric.length());
    }

}