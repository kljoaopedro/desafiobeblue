package br.com.beblue.desafio.desafioengenheirotecnico.repository.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.DiscoVenda;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.DiscoVendaBuilder;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.Venda;
import br.com.beblue.desafio.desafioengenheirotecnico.exception.LoadDataException;
import br.com.beblue.desafio.desafioengenheirotecnico.helper.PrePersist;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.venda.VendaMv;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.disco.DiscoService;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.spotify.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VendaService extends PrePersist<Venda> {

    @Autowired
    VendaRepository repository;

    @Autowired
    DiscoService discoService;

    @Autowired
    SpotifyService spotifyService;

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

    private List<Disco> searchDiscos(final GeneroEnum genero) {
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

