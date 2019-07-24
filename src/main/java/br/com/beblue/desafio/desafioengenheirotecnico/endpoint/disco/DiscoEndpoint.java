package br.com.beblue.desafio.desafioengenheirotecnico.endpoint.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.exception.LoadDataException;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.disco.PageDisco;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.disco.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> searchById(@PathVariable("id") String id) throws LoadDataException {
        Disco disco = service.searchById(id);
        return ResponseEntity.ok().body(disco);
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
    public ResponseEntity<?> searchAll(
            @RequestParam(value = "resultado", required = false, defaultValue = "25") Integer resultados,
            @RequestParam(value = "genero", required = false, defaultValue = "") GeneroEnum generoEnum) throws Exception, LoadDataException {
        try {
            PageDisco discos = service.searchAllPage(generoEnum, resultados);
            return ResponseEntity.ok().body(discos);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
