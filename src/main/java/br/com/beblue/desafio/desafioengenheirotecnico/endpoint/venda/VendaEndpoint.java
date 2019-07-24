package br.com.beblue.desafio.desafioengenheirotecnico.endpoint.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.Venda;
import br.com.beblue.desafio.desafioengenheirotecnico.exception.LoadDataException;
import br.com.beblue.desafio.desafioengenheirotecnico.pojo.venda.VendaMv;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.venda.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoint de Vendas.
 */
@RestController
@RequestMapping("/vendas/")
public class VendaEndpoint {

    //Injeção do serviço.
    @Autowired
    VendaService service;

    /**
     * Busca uma venda pelo seu identificador.
     *
     * @param id Identificador.
     * @return Venda.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable("id") final String id) throws LoadDataException {
        Venda venda = service.searchById(id);
        return ResponseEntity.ok().body(venda);
    }

    /**
     * Faz uma nova venda de acordo com a quantidade e o genero requisitado.
     *
     * @param vendaMv Objeto com o genero e a quantidade.
     * @return Nova Venda.
     * @throws Exception Exception.
     */
    @PostMapping(value = "/",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Venda> novaVenda(@RequestBody final VendaMv vendaMv) throws Exception, LoadDataException {
        try {
            Venda venda = service.novaVenda(vendaMv);
            return ResponseEntity.ok().body(venda);
        } catch (Throwable e) {
            throw new Exception(e.getMessage());
        }
    }
}
