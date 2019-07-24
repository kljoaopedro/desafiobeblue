package br.com.beblue.desafio.desafioengenheirotecnico.repository.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.helper.testes.TesteHelper;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DiscoServiceTest extends TesteHelper {

    DiscoService service = new DiscoService();

    @Test
    public void porcentagemDomingo() {
        BigDecimal popPercent = service.calcularPorcentagem(0, GeneroEnum.POP);
        BigDecimal mpbPercent = service.calcularPorcentagem(0, GeneroEnum.MPB);
        BigDecimal classicPercent = service.calcularPorcentagem(0, GeneroEnum.CLASSIC);
        BigDecimal rockPercent = service.calcularPorcentagem(0, GeneroEnum.ROCK);

        Assert.assertEquals(NOT_SAME, new BigDecimal("0.25"), popPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.30"), mpbPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.35"), classicPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.40"), rockPercent);
    }

    @Test
    public void porcentagemSegunda() {
        BigDecimal popPercent = service.calcularPorcentagem(1, GeneroEnum.POP);
        BigDecimal mpbPercent = service.calcularPorcentagem(1, GeneroEnum.MPB);
        BigDecimal classicPercent = service.calcularPorcentagem(1, GeneroEnum.CLASSIC);
        BigDecimal rockPercent = service.calcularPorcentagem(1, GeneroEnum.ROCK);

        Assert.assertEquals(NOT_SAME, new BigDecimal("0.07"), popPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.05"), mpbPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.03"), classicPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.10"), rockPercent);
    }

    @Test
    public void porcentagemTerca() {
        BigDecimal popPercent = service.calcularPorcentagem(2, GeneroEnum.POP);
        BigDecimal mpbPercent = service.calcularPorcentagem(2, GeneroEnum.MPB);
        BigDecimal classicPercent = service.calcularPorcentagem(2, GeneroEnum.CLASSIC);
        BigDecimal rockPercent = service.calcularPorcentagem(2, GeneroEnum.ROCK);

        Assert.assertEquals(NOT_SAME, new BigDecimal("0.06"), popPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.10"), mpbPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.05"), classicPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.15"), rockPercent);
    }

    @Test
    public void porcentagemQuarta() {
        BigDecimal popPercent = service.calcularPorcentagem(3, GeneroEnum.POP);
        BigDecimal mpbPercent = service.calcularPorcentagem(3, GeneroEnum.MPB);
        BigDecimal classicPercent = service.calcularPorcentagem(3, GeneroEnum.CLASSIC);
        BigDecimal rockPercent = service.calcularPorcentagem(3, GeneroEnum.ROCK);

        Assert.assertEquals(NOT_SAME, new BigDecimal("0.02"), popPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.15"), mpbPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.08"), classicPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.15"), rockPercent);
    }

    @Test
    public void porcentagemQuinta() {
        BigDecimal popPercent = service.calcularPorcentagem(4, GeneroEnum.POP);
        BigDecimal mpbPercent = service.calcularPorcentagem(4, GeneroEnum.MPB);
        BigDecimal classicPercent = service.calcularPorcentagem(4, GeneroEnum.CLASSIC);
        BigDecimal rockPercent = service.calcularPorcentagem(4, GeneroEnum.ROCK);

        Assert.assertEquals(NOT_SAME, new BigDecimal("0.10"), popPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.20"), mpbPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.13"), classicPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.15"), rockPercent);
    }

    @Test
    public void porcentagemSexta() {
        BigDecimal popPercent = service.calcularPorcentagem(5, GeneroEnum.POP);
        BigDecimal mpbPercent = service.calcularPorcentagem(5, GeneroEnum.MPB);
        BigDecimal classicPercent = service.calcularPorcentagem(5, GeneroEnum.CLASSIC);
        BigDecimal rockPercent = service.calcularPorcentagem(5, GeneroEnum.ROCK);

        Assert.assertEquals(NOT_SAME, new BigDecimal("0.15"), popPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.25"), mpbPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.18"), classicPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.20"), rockPercent);
    }

    @Test
    public void porcentagemSabado() {
        BigDecimal popPercent = service.calcularPorcentagem(6, GeneroEnum.POP);
        BigDecimal mpbPercent = service.calcularPorcentagem(6, GeneroEnum.MPB);
        BigDecimal classicPercent = service.calcularPorcentagem(6, GeneroEnum.CLASSIC);
        BigDecimal rockPercent = service.calcularPorcentagem(6, GeneroEnum.ROCK);

        Assert.assertEquals(NOT_SAME, new BigDecimal("0.20"), popPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.30"), mpbPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.25"), classicPercent);
        Assert.assertEquals(NOT_SAME, new BigDecimal("0.40"), rockPercent);
    }

    @Test
    public void generateRandomValue() {
        BigDecimal random = service.generateRandomValue();
        Assert.assertNotNull(random);
        Assert.assertEquals(3, random.toString().length());
    }

    @Test
    public void filterGenero() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Disco> toFilter = buildListDisco();
        Method method = service.getClass().getDeclaredMethod("filterGenero", GeneroEnum.class, List.class);
        method.setAccessible(true);

        Object rock = method.invoke(service, GeneroEnum.ROCK, toFilter);
        Object pop = method.invoke(service, GeneroEnum.POP, toFilter);
        Object mpb = method.invoke(service, GeneroEnum.MPB, toFilter);
        Object classic = method.invoke(service, GeneroEnum.CLASSIC, toFilter);

        List<Object> rockList = new ArrayList<>();
        List<Object> popList = new ArrayList<>();
        List<Object> mpbList = new ArrayList<>();
        List<Object> classicList = new ArrayList<>();

        rockList.addAll((Collection<?>) rock);
        popList.addAll((Collection<?>) pop);
        mpbList.addAll((Collection<?>) mpb);
        classicList.addAll((Collection<?>) classic);

        Assert.assertEquals(4, rockList.size());
        Assert.assertEquals(3, popList.size());
        Assert.assertEquals(2, mpbList.size());
        Assert.assertEquals(3, classicList.size());
    }


}