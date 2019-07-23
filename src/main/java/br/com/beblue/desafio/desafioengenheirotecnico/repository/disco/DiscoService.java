package br.com.beblue.desafio.desafioengenheirotecnico.repository.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.helper.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    public Disco searchById(final String id) {
        return repository.findById(id).get();
    }

    public List<Disco> searchAll() {
        return repository.findAll();
    }

    public void buildDisco(List<Disco> discos) {
        if (null != discos && !discos.isEmpty()) {
            discos.forEach(disco -> {
                disco.setValor(generateRandomValue());
                calcularCashBackDisco(disco);
            });
        }
    }


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

    private BigDecimal generateRandomValue() {
        return new BigDecimal(randomNumeric(3));
    }
}
