package br.com.beblue.desafio.desafioengenheirotecnico.endpoint.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.disco.PageDisco;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.disco.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable("id") String id) {
        Disco disco = service.searchById(id);
        return ResponseEntity.ok().body(disco);
    }

    @GetMapping("/")
    public ResponseEntity<?> searchAll(
            @RequestParam(value = "resultado", required = false, defaultValue = "25") Integer resultados,
            @RequestParam(value = "genero", required = false, defaultValue = "") GeneroEnum generoEnum) throws Exception {
        try {
            PageDisco discos = service.searchAllPage(generoEnum, resultados);
            return ResponseEntity.ok().body(discos);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
