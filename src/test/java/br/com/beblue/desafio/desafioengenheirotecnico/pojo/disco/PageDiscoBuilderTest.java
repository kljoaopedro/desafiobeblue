package br.com.beblue.desafio.desafioengenheirotecnico.pojo.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.helper.testes.TesteHelper;
import org.junit.Assert;
import org.junit.Test;

public class PageDiscoBuilderTest extends TesteHelper {

    @Test
    public void build() {
        PageDisco expected = new PageDisco();
        expected.setDiscos(buildListDisco());
        expected.setTotalElements(2L);
        expected.setTotalPages(5);

        PageDisco actual = PageDiscoBuilder
                .newInstance()
                .withDiscos(expected.getDiscos())
                .withTotalElements(expected.getTotalElements())
                .withTotalPages(expected.getTotalPages())
                .build();

        Assert.assertNotNull(actual);
        Assert.assertEquals(TesteHelper.NOT_SAME, expected.getDiscos().size(), actual.getDiscos().size());
        Assert.assertEquals(TesteHelper.NOT_SAME, expected.getTotalElements(), actual.getTotalElements());
        Assert.assertEquals(TesteHelper.NOT_SAME, expected.getTotalPages(), actual.getTotalPages());
    }
}