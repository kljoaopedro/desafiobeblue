package br.com.beblue.desafio.desafioengenheirotecnico.entity.disco;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class VendaMvBuilderTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void toJson() throws JsonProcessingException {
        VendaMv build = VendaMvBuilder.newInstance().withGeneroEnum(GeneroEnum.ROCK).withQuantidade(3).build();
        System.out.println(mapper.writeValueAsString(build));
    }

}