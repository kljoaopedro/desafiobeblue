package br.com.beblue.desafio.desafioengenheirotecnico.repository.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.VendaMv;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.DiscoVenda;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.DiscoVendaBuilder;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.Venda;
import br.com.beblue.desafio.desafioengenheirotecnico.helper.PrePersist;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.disco.DiscoService;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.spotify.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

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

    public Venda searchById(final String id) {
        return repository.findById(id).get();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Throwable.class)
    public Venda novaVenda(final VendaMv vendaMv) throws Exception {
        if (null == vendaMv) {
            throw new Exception("Conteúdo não pode ser vazio.");
        }
        spotifyService.clientCredentials_Sync();
//        Venda vendaAux = new Venda();
//        vendaAux.setValorTotal(BigDecimal.ZERO);
//        vendaAux.setTotalCashBack(BigDecimal.ZERO);
//        BigDecimal valorTotal = BigDecimal.ZERO;
//        BigDecimal totalCashBack = BigDecimal.ZERO;
//
//        List<Disco> discos = catalogo.getDiscos();
//
//        vendaAux.setTotalItens(discos.size());
//
//        for (Disco disco : discos) {
//            discoService.calcularCashBackDisco(disco);
//            valorTotal = valorTotal.add(disco.getValor());
//            totalCashBack = totalCashBack.add(disco.getCashBack());
//        }
//        vendaAux.setValorTotal(valorTotal);
//        vendaAux.setTotalCashBack(totalCashBack);
//        vendaAux.setDiscos(buildDiscoVenda(discos, vendaAux));
//
//        prePersist(vendaAux);
//        vendaAux.setId(randomAlphabetic(40));
//        vendaAux.getDiscos().forEach(x -> {
//            x.setId(randomAlphabetic(40));
//            x.setVenda(vendaAux);
//        });
//        repository.save(vendaAux);

        return null;
    }

    private List<DiscoVenda> buildDiscoVenda(List<Disco> discos, Venda vendaAux) {

        List<DiscoVenda> discoVendas = new ArrayList<>();
        for (Disco disco : discos) {
            discoVendas.add(
                    DiscoVendaBuilder.newInstance().withId(randomAlphabetic(40)).withDisco(disco).withVenda(vendaAux).build()
            );
        }
        return discoVendas;
    }

}

