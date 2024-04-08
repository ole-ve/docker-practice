package io.dpschool.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootApplication
@RestController
public class FrontendApplication {

    private static final RestTemplate restTemplate = new RestTemplate();

    /**
     * Backend Base URL. Would typically be replaced by an environment variable.
     */
    private static final String BACKEND_BASE_URL = "http://backend:3001";

    private static final String API_ENDPOINT_URL = BACKEND_BASE_URL + "/hello";

    @GetMapping("/")
    public String home() {
        return "Main page.";
    }

    @GetMapping("/backend")
    public String backend() {
        final ResponseEntity<String> backendResponse = restTemplate
                .getForEntity(URI.create(API_ENDPOINT_URL), String.class);
        String body = backendResponse.getBody();
        return String.format("Response from Backend: %s", body);
    }

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }

}
