package br.com.beblue.desafio.desafioengenheirotecnico.endpoint.disco;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogo/")
public class CatalogoEndpoint {

    @GetMapping("/all")
    public String searchAll() {
        return "Funcionou";
    }
}
