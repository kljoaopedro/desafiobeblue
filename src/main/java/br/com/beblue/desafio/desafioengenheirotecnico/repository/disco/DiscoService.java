package br.com.beblue.desafio.desafioengenheirotecnico.repository.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.exception.LoadDataException;
import br.com.beblue.desafio.desafioengenheirotecnico.helper.persistencia.PrePersist;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.disco.PageDisco;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.disco.PageDiscoBuilder;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.spotify.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@Service
public class DiscoService extends PrePersist<Disco> {

    @Autowired
    DiscoRepository repository;

    /**
     * Busca o Disco pelo seu ID.
     *
     * @param id Identificador do disco.
     * @return Disco.
     */
    public Disco searchById(final String id) throws LoadDataException {
        if (SpotifyService.needInit) {
            throw new LoadDataException("O Banco de dados não foi carregado.\n Utilizar a API : http://localhost:8080/spotify/init");
        }
        return repository.findById(id).get();
    }

    /**
     * Busca os discos pelo seu genero, ordenando pelo nome.
     *
     * @param genero Genero do disco.
     * @return Lista de Discos ordenadas de forma crescente pelo nome.
     * @throws LoadDataException Exceção se o banco nao for carregado.
     */
    public List<Disco> searchAll(final GeneroEnum genero) throws LoadDataException {
        if (SpotifyService.needInit) {
            throw new LoadDataException("O Banco de dados não foi carregado.\n Utilizar a API : http://localhost:8080/spotify/init");
        }
        List<Disco> all = repository.findAll();

        List<Disco> discos = filterGenero(genero, all);
        discos.sort(Comparator.comparing(Disco::getNome));
        return discos;
    }

    /**
     * Busca os discos de forma paginada.
     *
     * @param generoEnum Genero do disco.
     * @param resultados Resultados por página.
     * @return PageDisco com as informações sobre a paginação.
     * @throws Exception         Exception.
     * @throws LoadDataException Exceção se o banco nao for carregado.
     */
    public PageDisco searchAllPage(final GeneroEnum generoEnum, Integer resultados) throws Exception, LoadDataException {
        if (SpotifyService.needInit) {
            throw new LoadDataException("O Banco de dados não foi carregado.\n Utilizar a API : http://localhost:8080/spotify/init");
        }
        if (null == resultados || resultados <= 0) {
            throw new Exception("Resultado por página deve ser maior que 0!");
        }
        PageRequest pageRequest = PageRequest.of(0, resultados);
        Page<Disco> discoPage = repository.findAll(pageRequest);
        List<Disco> listAux = new ArrayList<>(discoPage.getContent());

        //paginas remanecentes
        while (discoPage.hasNext()) {
            Page<Disco> nextPage = repository.findAll(discoPage.nextPageable());
            listAux.addAll(nextPage.getContent());

            // Atualiza a pagina
            discoPage = nextPage;
        }
        List<Disco> discos = filterGenero(generoEnum, listAux);
        discos.sort(Comparator.comparing(Disco::getNome));

        return PageDiscoBuilder.
                newInstance()
                .withDiscos(discos)
                .withTotalElements(discos.size())
                .withTotalPages(discos.size() / resultados <= 0 ? 1 : discos.size() / resultados)
                .build();
    }

    /**
     * Filtra o disco pelo genero.
     *
     * @param genero genero do disco.
     * @param all    Lista de disco.
     * @return Lista de disco filtrada.
     */
    private List<Disco> filterGenero(GeneroEnum genero, List<Disco> all) {
        if (null != all && !all.isEmpty()) {
            if (null != genero) {
                return all.stream().filter(x -> (null != x.getGenero() && x.getGenero().equals(genero))).collect(Collectors.toList());
            }
            return all;
        }
        return null;
    }

    /**
     * Facilitador para construir um disco.
     *
     * @param discos Lista de disco.
     */
    public void buildDisco(List<Disco> discos) {
        if (null != discos && !discos.isEmpty()) {
            discos.forEach(disco -> {
                disco.setValor(generateRandomValue());
                calcularCashBackDisco(disco);
            });
        }
    }


    /**
     * Método que persiste um Disco.
     * Seta as informações necessárias antes de persistir.
     *
     * @param disco Disco a ser persistido.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Throwable.class)
    public void calcularCashBackDisco(final Disco disco) {
        if (null != disco) {
            BigDecimal percent = calcularPorcentagem(new Date().getDay(), disco.getGenero());
            BigDecimal valorCashBack = disco.getValor().multiply(percent);
            disco.setPorcentagemCashBack(percent);
            disco.setValorCashBack(valorCashBack);

            prePersist(disco);
            repository.save(disco);
        }
    }

    /**
     * Calcula a porcentagem do CashBack daquele disco de acordo com a tabela e o dia da semana.
     *
     * @param day    Dia da semana sendo 0 = Domingo e 6 = Sabado.
     * @param genero Genero do Disco.
     * @return Valor da porcentagem ja pronto para o calculo.
     */
    protected BigDecimal calcularPorcentagem(int day, GeneroEnum genero) {
        if (null != genero) {
            switch (genero) {
                case POP:
                    if (day == 0) {
                        return new BigDecimal("0.25");
                    }
                    if (day == 1) {
                        return new BigDecimal("0.07");
                    }
                    if (day == 2) {
                        return new BigDecimal("0.06");
                    }
                    if (day == 3) {
                        return new BigDecimal("0.02");
                    }
                    if (day == 4) {
                        return new BigDecimal("0.10");
                    }
                    if (day == 5) {
                        return new BigDecimal("0.15");
                    }
                    if (day == 6) {
                        return new BigDecimal("0.20");
                    }
                    break;
                case MPB:
                    if (day == 0) {
                        return new BigDecimal("0.30");
                    }
                    if (day == 1) {
                        return new BigDecimal("0.05");
                    }
                    if (day == 2) {
                        return new BigDecimal("0.10");
                    }
                    if (day == 3) {
                        return new BigDecimal("0.15");
                    }
                    if (day == 4) {
                        return new BigDecimal("0.20");
                    }
                    if (day == 5) {
                        return new BigDecimal("0.25");
                    }
                    if (day == 6) {
                        return new BigDecimal("0.30");
                    }
                    break;
                case CLASSIC:
                    if (day == 0) {
                        return new BigDecimal("0.35");
                    }
                    if (day == 1) {
                        return new BigDecimal("0.03");
                    }
                    if (day == 2) {
                        return new BigDecimal("0.05");
                    }
                    if (day == 3) {
                        return new BigDecimal("0.08");
                    }
                    if (day == 4) {
                        return new BigDecimal("0.13");
                    }
                    if (day == 5) {
                        return new BigDecimal("0.18");
                    }
                    if (day == 6) {
                        return new BigDecimal("0.25");
                    }
                    break;
                case ROCK:
                    if (day == 0) {
                        return new BigDecimal("0.40");
                    }
                    if (day == 1) {
                        return new BigDecimal("0.10");
                    }
                    if (day == 2) {
                        return new BigDecimal("0.15");
                    }
                    if (day == 3) {
                        return new BigDecimal("0.15");
                    }
                    if (day == 4) {
                        return new BigDecimal("0.15");
                    }
                    if (day == 5) {
                        return new BigDecimal("0.20");
                    }
                    if (day == 6) {
                        return new BigDecimal("0.40");
                    }
                    break;
            }
        }
        return null;
    }

    /**
     * Gera um valor randomico para o Disco.
     *
     * @return Número Randomico.
     */
    private BigDecimal generateRandomValue() {
        return new BigDecimal(randomNumeric(3));
    }
}
