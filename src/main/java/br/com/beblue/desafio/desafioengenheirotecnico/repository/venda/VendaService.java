package br.com.beblue.desafio.desafioengenheirotecnico.repository.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.DiscoVenda;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.DiscoVendaBuilder;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.Venda;
import br.com.beblue.desafio.desafioengenheirotecnico.exception.LoadDataException;
import br.com.beblue.desafio.desafioengenheirotecnico.helper.persistencia.PrePersist;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.venda.PageVenda;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.venda.PageVendaBuilder;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.venda.VendaMv;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.disco.DiscoService;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.spotify.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VendaService extends PrePersist<Venda> {

    @Autowired
    VendaRepository repository;

    @Autowired
    DiscoService discoService;

    @Autowired
    public VendaService(VendaRepository repository, DiscoService discoService) {
        this.repository = repository;
        this.discoService = discoService;
    }

    public Venda searchById(final String id) throws LoadDataException {
        if (SpotifyService.needInit) {
            throw new LoadDataException("O Banco de dados não foi carregado.\n Utilizar a API : http://localhost:8080/spotify/init");
        }
        return repository.findById(id).get();
    }

    public PageVenda searchAllPage(final Date dataInicial, final Date dataFinal, final Integer resultados) throws Exception, LoadDataException {
        if (SpotifyService.needInit) {
            throw new LoadDataException("O Banco de dados não foi carregado.\n Utilizar a API : http://localhost:8080/spotify/init");
        }
        if (null == resultados || resultados <= 0) {
            throw new Exception("Resultado por página deve ser maior que 0!");
        }
        PageRequest pageRequest = PageRequest.of(0, resultados);
        Page<Venda> vendaPage = repository.findAll(pageRequest);
        List<Venda> listAux = new ArrayList<>(vendaPage.getContent());

        //paginas remanecentes
        while (vendaPage.hasNext()) {
            Page<Venda> nextPage = repository.findAll(vendaPage.nextPageable());
            listAux.addAll(nextPage.getContent());

            // Atualiza a pagina
            vendaPage = nextPage;
        }
        List<Venda> vendas = filterData(listAux, dataInicial, dataFinal);
        Collections.sort(vendas, new Comparator<Venda>() {
            @Override
            public int compare(Venda venda1, Venda venda2) {
                return venda2.getDataVenda().compareTo(venda1.getDataVenda());
            }
        });
        return PageVendaBuilder
                .newInstance()
                .withVendas(vendas)
                .withTotalElements(vendas.size())
                .withTotalPages(vendas.size() / resultados <= 0 ? 1 : vendas.size() / resultados)
                .build();
    }

    private List<Venda> filterData(List<Venda> listAux, Date dataInicial, Date dataFinal) {
        return listAux.stream().filter(
                x -> (x != null && (x.getDataVenda().after(dataInicial) && x.getDataVenda().before(dataFinal)))
        ).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Throwable.class)
    public Venda novaVenda(final VendaMv vendaMv) throws Exception, LoadDataException {
        if (null == vendaMv) {
            throw new Exception("Conteúdo não pode ser vazio.");
        }
        if (SpotifyService.needInit) {
            throw new LoadDataException("O Banco de dados não foi carregado.\n Utilizar a API : http://localhost:8080/spotify/init");
        }

        List<Disco> discosFiltrados = searchDiscos(vendaMv.getGeneroEnum());
        List<Disco> discoAux = new ArrayList<>();

        for (int i = 0; i < vendaMv.getQuantidade(); i++) {
            discoAux.add(discosFiltrados.get(i));
        }

        Venda vendaAux = new Venda();
        vendaAux.setValorTotal(BigDecimal.ZERO);
        vendaAux.setTotalCashBack(BigDecimal.ZERO);
        BigDecimal valorTotal = BigDecimal.ZERO;
        BigDecimal totalCashBack = BigDecimal.ZERO;

        for (Disco disco : discoAux) {
            valorTotal = valorTotal.add(disco.getValor());
            totalCashBack = totalCashBack.add(disco.getValorCashBack());
        }
        vendaAux.setTotalItens(discoAux.size());
        vendaAux.setDataVenda(new Date());
        vendaAux.setValorTotal(valorTotal);
        vendaAux.setTotalCashBack(totalCashBack);
        vendaAux.setDiscos(buildDiscoVenda(discoAux, vendaAux));

        prePersist(vendaAux);
        repository.save(vendaAux);

        return vendaAux;
    }

    private List<Disco> searchDiscos(final GeneroEnum genero) throws LoadDataException {
        return discoService.searchAll(genero);
    }

    private List<DiscoVenda> buildDiscoVenda(List<Disco> discos, Venda vendaAux) {

        List<DiscoVenda> discoVendas = new ArrayList<>();
        for (Disco disco : discos) {
            discoVendas.add(
                    DiscoVendaBuilder.newInstance().withId(UUID.randomUUID().toString()).withDisco(disco).withVenda(vendaAux).build()
            );
        }
        return discoVendas;
    }

}

