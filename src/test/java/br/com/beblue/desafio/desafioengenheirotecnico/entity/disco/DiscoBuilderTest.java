package br.com.beblue.desafio.desafioengenheirotecnico.entity.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.helper.testes.TesteHelper;
import org.junit.Assert;
import org.junit.Test;

public class DiscoBuilderTest extends TesteHelper {


    @Test
    public void build() {
        Disco expected = buildDisco();

        Disco actual = DiscoBuilder
                .newInstance()
                .withId(expected.getId())
                .withNome(expected.getNome())
                .withGenero(expected.getGenero())
                .withValor(expected.getValor())
                .withCashBack(expected.getValorCashBack())
                .withPorcentagemCashBack(expected.getPorcentagemCashBack())
                .build();

        Assert.assertNotNull(actual);
        Assert.assertEquals(TesteHelper.NOT_SAME, expected, actual);
    }
}