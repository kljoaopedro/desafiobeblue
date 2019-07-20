package br.com.beblue.desafio.desafioengenheirotecnico.repository.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.*;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.Venda;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.disco.DiscoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class VendaServiceTest {

    DiscoService discoService = new DiscoService();
    VendaService service = new VendaService(null, discoService);

    @Test
    public void novaVenda() throws Exception {
        Venda venda = service.novaVenda(buildCatalogo());
        Assert.assertNotNull(venda);
        Assert.assertEquals(new BigDecimal(600), venda.getValorTotal());
    }

    private Catalogo buildCatalogo() {
        List<Disco> discos = Arrays.asList(
                DiscoBuilder.newInstance().withId("1").withValor(new BigDecimal(100)).withGenero(GeneroEnum.ROCK).build(),
                DiscoBuilder.newInstance().withId("2").withValor(new BigDecimal(200)).withGenero(GeneroEnum.ROCK).build(),
                DiscoBuilder.newInstance().withId("3").withValor(new BigDecimal(300)).withGenero(GeneroEnum.ROCK).build()
        );
        return CatalogoBuilder.newInstance().withDiscos(discos).build();
    }

    @Test
    public void transformToJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(buildCatalogo());
        System.out.println(json);

    }
}