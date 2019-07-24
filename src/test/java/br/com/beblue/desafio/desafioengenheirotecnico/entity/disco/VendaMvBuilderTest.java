package br.com.beblue.desafio.desafioengenheirotecnico.entity.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.helper.testes.TesteHelper;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.venda.VendaMv;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.venda.VendaMvBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class VendaMvBuilderTest extends TesteHelper {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void toJson() throws JsonProcessingException {
        VendaMv build = VendaMvBuilder.newInstance().withGeneroEnum(GeneroEnum.ROCK).withQuantidade(3).build();
        System.out.println(mapper.writeValueAsString(build));
    }

    @Test
    public void build() {
        VendaMv expected = new VendaMv();
        expected.setGeneroEnum(GeneroEnum.ROCK);
        expected.setQuantidade(5);

        VendaMv actual = VendaMvBuilder
                .newInstance()
                .withGeneroEnum(expected.getGeneroEnum())
                .withQuantidade(expected.getQuantidade())
                .build();

        Assert.assertNotNull(actual);
        Assert.assertEquals(TesteHelper.NOT_SAME, expected.getGeneroEnum(), actual.getGeneroEnum());
        Assert.assertEquals(TesteHelper.NOT_SAME, expected.getQuantidade(), actual.getQuantidade());
    }

}