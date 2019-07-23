package br.com.beblue.desafio.desafioengenheirotecnico.endpoint.spotify;

import br.com.beblue.desafio.desafioengenheirotecnico.repository.spotify.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpotifyEndpoint {

    @Autowired
    SpotifyService service;

    @RequestMapping(path = "/spotify/init")
    public void init() {
        service.init();
    }

}
