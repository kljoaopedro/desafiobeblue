package br.com.beblue.desafio.desafioengenheirotecnico.entity.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.helper.testes.TesteHelper;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.UUID;

public class DiscoVendaBuilderTest extends TesteHelper {

    @Test
    public void build() throws ParseException {
        DiscoVenda expected = new DiscoVenda();
        expected.setId(UUID.randomUUID().toString());
        expected.setDisco(buildDisco());
        expected.setVenda(buildVenda());

        DiscoVenda actual = DiscoVendaBuilder
                .newInstance()
                .withId(expected.getId())
                .withDisco(expected.getDisco())
                .withVenda(expected.getVenda())
                .build();

        Assert.assertNotNull(actual);
        Assert.assertEquals(TesteHelper.NOT_SAME, expected, actual);
    }
}