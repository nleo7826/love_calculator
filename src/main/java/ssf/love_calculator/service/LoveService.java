package ssf.love_calculator.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import ssf.love_calculator.model.Compatability;

@Qualifier("LoveService")
@Service
public class LoveService {

    private static final String LOVE_CALCULATOR_URL = "https://love-calculator.p.rapidapi.com/getPercentage";

    public Optional<Compatability> getCompatability(String sname, String fname) throws IOException, InterruptedException {
        
        String luri = UriComponentsBuilder
                                    .fromUriString(LOVE_CALCULATOR_URL)
                                    .queryParam("sname", sname)
                                    .queryParam("fname", fname)
                                    .toUriString();

        //String apikey = System.getenv("LOVE_CACULATOR_API_KEY");
        // String apikey = "578e764034mshc8e96f75073228cp1d28e2jsn209a7809b08c";

        // HttpHeaders headers = new HttpHeaders();
        // headers.set("X-RapidAPI-Key", apikey);
        // headers.set("X-RapidAPI-Host", "love-calculator.p.rapidapi.com");
        // HttpEntity<String> entity = new HttpEntity<>(headers);

        // ResponseEntity<String> response = restTemplate.exchange(luri, HttpMethod.POST, entity, String.class);
        // System.out.println(response.getBody());            
        
        // Compatability c = Compatability.create(response.getBody());
        // System.out.println(c);

        HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(luri))
                        .header("X-RapidAPI-Key", "578e764034mshc8e96f75073228cp1d28e2jsn209a7809b08c")
                        .header("X-RapidAPI-Host", "love-calculator.p.rapidapi.com")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        
        Compatability c = Compatability.create(response.body());
        System.out.println(c);

        if(c != null)
            return Optional.of(c);                        
        return Optional.empty();
    }
}
