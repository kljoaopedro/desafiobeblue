package br.com.beblue.desafio.desafioengenheirotecnico.repository.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.DiscoVenda;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.Venda;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.VendaBuilder;
import br.com.beblue.desafio.desafioengenheirotecnico.helper.testes.TesteHelper;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.disco.DiscoService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VendaServiceTest extends TesteHelper {

    DiscoService discoService = new DiscoService();
    VendaService service = new VendaService(null, discoService);

    @Test
    public void filterData() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        Method method = service.getClass().getDeclaredMethod("filterData", List.class, Date.class, Date.class);
        method.setAccessible(true);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data1 = sdf.parse("01/04/2019");
        Date data2 = sdf.parse("02/04/2019");
        Date data3 = sdf.parse("03/04/2019");

        List<Venda> vendas = buildListVenda(data1, data2, data3);
        Object vendaFilter = method.invoke(service, vendas, data1, data3);
        List<Venda> vendaAux = new ArrayList<>();
        vendaAux.addAll((Collection<? extends Venda>) vendaFilter);

        Assert.assertNotNull(vendaAux);
        Assert.assertEquals(TesteHelper.NOT_SAME, data2, vendaAux.get(0).getDataVenda());
    }

    @Test
    public void buildDiscoVenda() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = service.getClass().getDeclaredMethod("buildDiscoVenda", List.class, Venda.class);
        method.setAccessible(true);

        Object discoVenda = method.invoke(service, buildListDisco(), VendaBuilder.newInstance().withId(UUID.randomUUID().toString()).build());
        List<DiscoVenda> discoVendaAux = new ArrayList<>();

        discoVendaAux.addAll((Collection<? extends DiscoVenda>) discoVenda);

        Assert.assertNotNull(discoVendaAux);
        Assert.assertEquals(12, discoVendaAux.size());

        discoVendaAux.forEach(x -> {
            Assert.assertNotNull(x.getId());
            Assert.assertEquals(TesteHelper.NOT_SAME, 36, x.getId().length());
        });
    }

    @Test
    public void getRandom() {
        for (int i = 0; i < 10; i++) {
            int randomNumeric = service.getRandomNumeric();
            Assert.assertTrue(randomNumeric >= 0);
            Assert.assertTrue(randomNumeric <= 49);
        }

    }
}