package br.com.beblue.desafio.desafioengenheirotecnico.helper.testes;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.Venda;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TesteHelperTest {

    TesteHelper helper = new TesteHelper();

    @Test
    public void buildListDisco() {
        List<Disco> discos = helper.buildListDisco();

        Assert.assertNotNull(discos);
        Assert.assertEquals(TesteHelper.NOT_SAME, 12, discos.size());
    }

    @Test
    public void buildListVenda() {
        List<Venda> vendas = helper.buildListVenda(new Date(), new Date(), new Date());

        Assert.assertNotNull(vendas);
        Assert.assertEquals(TesteHelper.NOT_SAME, 3, vendas.size());
    }

    @Test
    public void buildDisco() {
        Disco disco = helper.buildDisco();

        Assert.assertNotNull(disco);
        Assert.assertEquals(TesteHelper.NOT_SAME, 36, disco.getId().length());
        Assert.assertEquals(TesteHelper.NOT_SAME, BigDecimal.TEN, disco.getValor());
        Assert.assertEquals(TesteHelper.NOT_SAME, 5, disco.getNome().length());
        Assert.assertEquals(TesteHelper.NOT_SAME, BigDecimal.ONE, disco.getPorcentagemCashBack());
        Assert.assertEquals(TesteHelper.NOT_SAME, BigDecimal.ZERO, disco.getValorCashBack());
        Assert.assertEquals(TesteHelper.NOT_SAME, GeneroEnum.ROCK, disco.getGenero());
    }

    @Test
    public void buildVenda() throws ParseException {
        Venda venda = helper.buildVenda();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataExpected = sdf.parse("01/04/1999");

        Assert.assertNotNull(venda);
        Assert.assertEquals(TesteHelper.NOT_SAME, 36, venda.getId().length());
        Assert.assertEquals(TesteHelper.NOT_SAME, BigDecimal.ZERO, venda.getTotalCashBack());
        Assert.assertEquals(TesteHelper.NOT_SAME, BigDecimal.TEN, venda.getValorTotal());
        Assert.assertEquals(TesteHelper.NOT_SAME, new Integer(5), venda.getTotalItens());
        Assert.assertEquals(TesteHelper.NOT_SAME, dataExpected, venda.getDataVenda());
        Assert.assertNull(TesteHelper.NOT_SAME, venda.getDiscos());
    }
}