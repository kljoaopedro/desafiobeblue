package br.com.beblue.desafio.desafioengenheirotecnico.repository.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.repository.disco.DiscoService;

public class VendaServiceTest {

    DiscoService discoService = new DiscoService();
    VendaService service = new VendaService(null, discoService);

}