package br.com.beblue.desafio.desafioengenheirotecnico.endpoint.disco;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.disco.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disco/")
public class DiscoEndpoint {

    @Autowired
    DiscoService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> searchById(@PathVariable("id") String id) {
        Disco disco = service.searchById(id);
        return ResponseEntity.ok().body(disco);
    }
}
