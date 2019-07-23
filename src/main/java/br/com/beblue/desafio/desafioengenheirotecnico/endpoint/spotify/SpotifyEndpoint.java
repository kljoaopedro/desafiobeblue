package br.com.beblue.desafio.desafioengenheirotecnico.endpoint.spotify;

import br.com.beblue.desafio.desafioengenheirotecnico.repository.spotify.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint que inicia a carga no banco de dados.
 */
@RestController
public class SpotifyEndpoint {

    //Injeção do serviço do spotify.
    @Autowired
    SpotifyService service;

    /**
     * Inicia o banco de dados com os 50 discos de cada genero.
     */
    @RequestMapping(path = "/spotify/init")
    public void init() throws Exception {
        try {
            service.init();
        } catch (Throwable e) {
            throw new Exception(e.getMessage());
        }
    }

}
