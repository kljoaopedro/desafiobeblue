package br.com.beblue.desafio.desafioengenheirotecnico.repository.spotify;

import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.Disco;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.DiscoBuilder;
import br.com.beblue.desafio.desafioengenheirotecnico.entity.disco.GeneroEnum;
import br.com.beblue.desafio.desafioengenheirotecnico.repository.disco.DiscoService;
import com.google.common.base.Strings;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsavel pelas operações envolvendo a API do Spotify.
 */
@Service
public class SpotifyService {

    //Client ID e Secret da API do Spotify adquirida ao registrar o projeto.
    private static final String clientId = "50e6ecc7978e4031b33ee387fd9a16e6";
    private static final String clientSecret = "fe726b7a7d3644459dafbb251dd0ece0";
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();
    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();
    public static boolean needInit = true;
    static boolean popLoaded = false;
    static boolean mpbLoaded = false;
    static boolean classicLoaded = false;
    //Atributos garantem apenas uma carga no banco enquanto o serviço estiver no ar.
    static boolean rockLoaded = false;
    //Injeção do serviço de disco.
    @Autowired
    DiscoService discoService;

    /**
     * Metodo ponte para iniciar a carga no banco com os discos vindo do spotify.
     */
    public void init() {
        if (needInit) {
            needInit = false;
            clientCredentials_Sync();
        }
    }

    /**
     * Metodo responsavel pela comunicaçao com a biblioteca do spotify
     * Ja gerando o acessToken necessário para fazer as consultas.
     */
    private void clientCredentials_Sync() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            if (clientCredentials.getExpiresIn() <= 0) {
                spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            }
            bridgeAcessToken(clientCredentials.getAccessToken());
            System.out.println("Expira em: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Ponto para fazer as consultas pelos discos e armazenar no banco de dado.
     *
     * @param accessToken acessToken gerado pelo método que o chamou.
     * @throws IOException            exception
     * @throws SpotifyWebApiException exception
     */
    private void bridgeAcessToken(String accessToken) throws IOException, SpotifyWebApiException {
        if (Strings.isNullOrEmpty(accessToken)) {
            this.clientCredentials_Sync();
        }
        SpotifyApi request = new SpotifyApi.Builder()
                .setAccessToken(accessToken)
                .build();
        searchRock(request);
        searchPop(request);
        searchMpb(request);
        searchClassic(request);
    }

    /**
     * Faz as busca pelos discos do genêro de Rock.
     *
     * @param request Objeto do spotify com os dados necessários para o request.
     * @throws IOException            Exception.
     * @throws SpotifyWebApiException Exception.
     */
    public void searchRock(final SpotifyApi request) throws IOException, SpotifyWebApiException {
        Assert.notNull(request, "Não existe acessToken");
        if (!rockLoaded) {
            SearchArtistsRequest build = request.searchArtists("Beatles").limit(50).build();
            Artist[] artistas = build.execute().getItems();

            SearchAlbumsRequest search = request.searchAlbums(artistas[0].getName()).limit(50).build();
            AlbumSimplified[] rock = search.execute().getItems();

            List<Disco> discosRock = new ArrayList<>();

            for (int i = 0; i < rock.length; i++) {
                discosRock.add(i,
                        DiscoBuilder
                                .newInstance()
                                .withNome(rock[i].getName())
                                .withGenero(GeneroEnum.ROCK)
                                .build()
                );
            }
            rockLoaded = true;
            discoService.buildDisco(discosRock);
        }

    }

    /**
     * Faz as busca pelos discos do genêro de Pop.
     *
     * @param request Objeto do spotify com os dados necessários para o request.
     * @throws IOException            Exception.
     * @throws SpotifyWebApiException Exception.
     */
    public void searchPop(final SpotifyApi request) throws IOException, SpotifyWebApiException {
        Assert.notNull(request, "Não existe acessToken");
        if (!popLoaded) {
            SearchArtistsRequest build = request.searchArtists("Michael Jackson").limit(50).build();
            Artist[] artistas = build.execute().getItems();

            SearchAlbumsRequest search = request.searchAlbums(artistas[0].getName()).limit(50).build();
            AlbumSimplified[] pop = search.execute().getItems();

            List<Disco> discosRock = new ArrayList<>();

            for (int i = 0; i < pop.length; i++) {
                discosRock.add(i,
                        DiscoBuilder
                                .newInstance()
                                .withNome(pop[i].getName())
                                .withGenero(GeneroEnum.POP)
                                .build()
                );
            }
            popLoaded = true;
            discoService.buildDisco(discosRock);
        }

    }

    /**
     * Faz as busca pelos discos do genêro de MPB.
     *
     * @param request Objeto do spotify com os dados necessários para o request.
     * @throws IOException            Exception.
     * @throws SpotifyWebApiException Exception.
     */
    public void searchMpb(final SpotifyApi request) throws IOException, SpotifyWebApiException {
        Assert.notNull(request, "Não existe acessToken");
        if (!mpbLoaded) {
            SearchArtistsRequest build = request.searchArtists("MPB").limit(50).build();
            Artist[] artistas = build.execute().getItems();

            SearchAlbumsRequest search = request.searchAlbums(artistas[0].getName()).limit(50).build();
            AlbumSimplified[] mpb = search.execute().getItems();

            List<Disco> discosRock = new ArrayList<>();

            for (int i = 0; i < mpb.length; i++) {
                discosRock.add(i,
                        DiscoBuilder
                                .newInstance()
                                .withNome(mpb[i].getName())
                                .withGenero(GeneroEnum.MPB)
                                .build()
                );
            }
            mpbLoaded = true;
            discoService.buildDisco(discosRock);
        }

    }

    /**
     * Faz as busca pelos discos do genêro Clássico.
     *
     * @param request Objeto do spotify com os dados necessários para o request.
     * @throws IOException            Exception.
     * @throws SpotifyWebApiException Exception.
     */
    public void searchClassic(final SpotifyApi request) throws IOException, SpotifyWebApiException {
        Assert.notNull(request, "Não existe acessToken");
        if (!classicLoaded) {
            SearchArtistsRequest build = request.searchArtists("Ludwig van Beethoven").limit(50).build();
            Artist[] artistas = build.execute().getItems();

            SearchAlbumsRequest search = request.searchAlbums(artistas[0].getName()).limit(50).build();
            AlbumSimplified[] classic = search.execute().getItems();

            List<Disco> discosRock = new ArrayList<>();

            for (int i = 0; i < classic.length; i++) {
                discosRock.add(i,
                        DiscoBuilder
                                .newInstance()
                                .withNome(classic[i].getName())
                                .withGenero(GeneroEnum.CLASSIC)
                                .build()
                );
            }
            classicLoaded = true;
            discoService.buildDisco(discosRock);
        }

    }


}
