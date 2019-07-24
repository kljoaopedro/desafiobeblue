package br.com.beblue.desafio.desafioengenheirotecnico.pojo.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.helper.testes.TesteHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class PageVendaBuilderTest extends TesteHelper {

    @Test
    public void build() {
        PageVenda expected = new PageVenda();
        expected.setVendas(buildListVenda(new Date(), new Date(), new Date()));
        expected.setTotalElements(2L);
        expected.setTotalPages(5);

        PageVenda actual = PageVendaBuilder
                .newInstance()
                .withVendas(expected.getVendas())
                .withTotalElements(expected.getTotalElements())
                .withTotalPages(expected.getTotalPages())
                .build();

        Assert.assertNotNull(actual);
        Assert.assertEquals(TesteHelper.NOT_SAME, expected.getVendas().size(), actual.getVendas().size());
        Assert.assertEquals(TesteHelper.NOT_SAME, expected.getTotalElements(), actual.getTotalElements());
        Assert.assertEquals(TesteHelper.NOT_SAME, expected.getTotalPages(), actual.getTotalPages());
    }
}