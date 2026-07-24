package misbahul.uz.misbahul.service;

import misbahul.uz.misbahul.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value; // Muhim import
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WeatherService {

    private final WebClient webClient;

    // Properties fayldan qiymatni o'qib oladi
    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherService(WebClient.Builder webClientBuilder,
            @Value("${weather.api.url}") String baseUrl) {
        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .build();
    }

    public WeatherResponse getWeather(String city) {
        try {
            return this.webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/weather")
                            .queryParam("q", city)
                            .queryParam("units", "metric")
                            .queryParam("lang", "uz")
                            .queryParam("appid", apiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(WeatherResponse.class)
                    .block();
        } catch (Exception e) {
            System.err.println("Xatolik: " + e.getMessage());
            return null;
        }
    }
}