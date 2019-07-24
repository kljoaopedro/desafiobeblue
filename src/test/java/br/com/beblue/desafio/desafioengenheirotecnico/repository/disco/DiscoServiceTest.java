package br.com.beblue.desafio.desafioengenheirotecnico.repository.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.exception.LoadDataException;
import br.com.beblue.desafio.desafioengenheirotecnico.helper.testes.MessageValidation;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.spotify.SpotifyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(SpotifyService.class)
public class DiscoServiceTest extends MessageValidation {

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
    public void searchByIdPositive() throws LoadDataException {
        DiscoRepository repositoryMock = Mockito.mock(DiscoRepository.class);
        PowerMockito.mockStatic(SpotifyService.class);

        DiscoService serviceMock = new DiscoService();
        serviceMock = spy(serviceMock);
        SpotifyService.needInit = false;

        doReturn(new Disco()).when(repositoryMock).findById(anyString());
        doCallRealMethod().when(serviceMock).searchById(anyString());

        serviceMock.searchById("123");
        verify(repositoryMock, times(1)).findById(eq("123"));
    }
}