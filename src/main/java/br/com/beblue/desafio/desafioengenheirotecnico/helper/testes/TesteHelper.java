package br.com.beblue.desafio.desafioengenheirotecnico.helper.testes;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.DiscoBuilder;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.Venda;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.VendaBuilder;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * Classe que ajuda nos Testes do sistema.
 */
public class TesteHelper {
    public static final String NOT_SAME = "Objeto atual diferente do esperado.";

    public List<Disco> buildListDisco() {
        return Arrays.asList(
                DiscoBuilder.newInstance().withGenero(GeneroEnum.ROCK).build(),
                DiscoBuilder.newInstance().withGenero(GeneroEnum.ROCK).build(),
                DiscoBuilder.newInstance().withGenero(GeneroEnum.ROCK).build(),
                DiscoBuilder.newInstance().withGenero(GeneroEnum.ROCK).build(),
                DiscoBuilder.newInstance().withGenero(GeneroEnum.CLASSIC).build(),
                DiscoBuilder.newInstance().withGenero(GeneroEnum.CLASSIC).build(),
                DiscoBuilder.newInstance().withGenero(GeneroEnum.CLASSIC).build(),
                DiscoBuilder.newInstance().withGenero(GeneroEnum.MPB).build(),
                DiscoBuilder.newInstance().withGenero(GeneroEnum.MPB).build(),
                DiscoBuilder.newInstance().withGenero(GeneroEnum.POP).build(),
                DiscoBuilder.newInstance().withGenero(GeneroEnum.POP).build(),
                DiscoBuilder.newInstance().withGenero(GeneroEnum.POP).build()
        );
    }

    public List<Venda> buildListVenda(Date data1, Date data2, Date data3) {
        return Arrays.asList(
                VendaBuilder.newInstance().withDataVenda(data1).build(),
                VendaBuilder.newInstance().withDataVenda(data2).build(),
                VendaBuilder.newInstance().withDataVenda(data3).build()
        );
    }

    public Disco buildDisco() {
        Disco expected = new Disco();
        expected.setId(UUID.randomUUID().toString());
        expected.setValor(BigDecimal.TEN);
        expected.setNome(randomAlphabetic(5));
        expected.setPorcentagemCashBack(BigDecimal.ONE);
        expected.setValorCashBack(BigDecimal.ZERO);
        expected.setGenero(GeneroEnum.ROCK);
        return expected;
    }

    public Venda buildVenda() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Venda expected = new Venda();
        expected.setId(UUID.randomUUID().toString());
        expected.setTotalCashBack(BigDecimal.ZERO);
        expected.setValorTotal(BigDecimal.TEN);
        expected.setTotalItens(5);
        expected.setDataVenda(sdf.parse("01/04/1999"));
        expected.setDiscos(null);
        return expected;
    }

}
