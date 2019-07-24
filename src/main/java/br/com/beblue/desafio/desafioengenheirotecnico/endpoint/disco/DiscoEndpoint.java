package br.com.beblue.desafio.desafioengenheirotecnico.endpoint.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.exception.LoadDataException;
import br.com.beblue.desafio.desafioengenheirotecnico.helper.wrapper.Wrapper;
import br.com.beblue.desafio.desafioengenheirotecnico.helper.wrapper.WrapperBuilder;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.disco.PageDisco;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.disco.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoint dos Discos.
 */
@RestController
@RequestMapping("/disco/")
public class DiscoEndpoint {

    //Injeção do serviço.
    @Autowired
    DiscoService service;

    /**
     * Busca um disco pelo seu identificador.
     *
     * @param id identificador.
     * @return Disco.
     */
    @GetMapping(
            path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Wrapper<Disco> searchById(@PathVariable("id") String id) throws LoadDataException {
        try {
            Disco disco = service.searchById(id);
            return WrapperBuilder.newInstance().withStatus(200).withBody(disco).build();
        } catch (Throwable e) {
            return WrapperBuilder.newInstance().withStatus(409).withError(e.getMessage()).withBody(null).build();
        }
    }

    /**
     * Busca todos os Discos de forma paginada.
     *
     * @param resultados resultado por página.
     * @param generoEnum Genero do disco.
     * @return Wrapper com as informações.
     * @throws Exception         Exception.
     * @throws LoadDataException Exceção se o banco nao for carregado.
     */
    @GetMapping(
            path = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Wrapper<PageDisco> searchAll(
            @RequestParam(value = "resultado", required = false, defaultValue = "25") Integer resultados,
            @RequestParam(value = "genero", required = false, defaultValue = "") GeneroEnum generoEnum) throws Exception, LoadDataException {
        try {
            PageDisco discos = service.searchAllPage(generoEnum, resultados);
            return WrapperBuilder.newInstance().withStatus(200).withBody(discos).build();
        } catch (Exception e) {
            return WrapperBuilder.newInstance().withStatus(409).withError(e.getMessage()).withBody(null).build();
        }
    }
}
