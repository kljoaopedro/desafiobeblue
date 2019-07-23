package br.com.beblue.desafio.desafioengenheirotecnico.repository.spotify;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.junit.Test;

import java.io.IOException;

public class SpotifyServiceTest {

    SpotifyService spotifyService = new SpotifyService();

    @Test
    public void request() throws IOException, SpotifyWebApiException {


        ObjectMapper mapper = new ObjectMapper();

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken("BQD96wbACK54kNWGHsYEC1l20M2JRrHsoFRyNfTEDPXSiaZCK2kp7uUkG9SbplFrC0ebqdcsPMaFr67lGf4")
                .build();

        SearchTracksRequest rock = spotifyApi.searchTracks("ROCK")
                .q("ROCK")
                .limit(50)
                .build();

        Paging<Track> execute = rock.execute();
        Track[] items = execute.getItems();
        String s = mapper.writeValueAsString(items);
        System.out.println(s);
        System.out.println(items[0].getName());
    }

    @Test
    public void acess() {
        spotifyService.init();
    }

}