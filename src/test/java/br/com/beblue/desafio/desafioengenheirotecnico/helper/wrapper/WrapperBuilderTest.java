package br.com.beblue.desafio.desafioengenheirotecnico.helper.wrapper;

import br.com.beblue.desafio.desafioengenheirotecnico.helper.testes.TesteHelper;
import org.junit.Assert;
import org.junit.Test;

public class WrapperBuilderTest extends TesteHelper {

    @Test
    public void build() {
        Wrapper<String> expected = new Wrapper<>();
        expected.setError("ERRO");
        expected.setBody("BODY");

        Wrapper actual = WrapperBuilder.newInstance()
                .withError(expected.getError())
                .withBody(expected.getBody())
                .build();

        Assert.assertNotNull(actual);
        Assert.assertEquals(TesteHelper.NOT_SAME, expected.getError(), actual.getError());
        Assert.assertEquals(TesteHelper.NOT_SAME, expected.getBody(), actual.getBody());
    }

}