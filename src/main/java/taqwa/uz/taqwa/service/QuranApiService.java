package taqwa.uz.taqwa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class QuranApiService {

    private final WebClient.Builder webClientBuilder;

    public QuranApiService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Object fetchSurahFromApi(Integer surahId) {
        return webClientBuilder.build()
                .get()
                .uri("https://api.alquran.cloud/v1/surah/{id}/editions/quran-uthmani,uz.sodik,en.sahih", surahId)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}