package br.com.beblue.desafio.desafioengenheirotecnico.entity.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.helper.testes.TesteHelper;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

public class VendaBuilderTest extends TesteHelper {

    @Test
    public void build() throws ParseException {
        Venda expected = buildVenda();

        Venda actual = VendaBuilder
                .newInstance()
                .withId(expected.getId())
                .withValorTotal(expected.getValorTotal())
                .withTotalItens(expected.getTotalItens())
                .withDataVenda(expected.getDataVenda())
                .withDiscosVenda(expected.getDiscos())
                .withTotalCashBack(expected.getTotalCashBack())
                .build();

        Assert.assertNotNull(actual);
        Assert.assertEquals(TesteHelper.NOT_SAME, expected, actual);
    }

}