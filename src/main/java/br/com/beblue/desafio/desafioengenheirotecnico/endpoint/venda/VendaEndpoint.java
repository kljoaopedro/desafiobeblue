package br.com.beblue.desafio.desafioengenheirotecnico.endpoint.venda;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Catalogo;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.venda.Venda;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.venda.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendas/")
public class VendaEndpoint {

    @Autowired
    VendaService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable("id") final String id) {
        Venda venda = service.searchById(id);
        return ResponseEntity.ok().body(venda);
    }

    @PostMapping(value = "/",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Venda> novaVenda(@RequestBody final Catalogo catalogo) throws Exception {
        try {
            Venda venda = service.novaVenda(catalogo);
            return ResponseEntity.ok().body(venda);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
