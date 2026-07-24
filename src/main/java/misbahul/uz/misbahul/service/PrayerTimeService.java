package misbahul.uz.misbahul.service;

import misbahul.uz.misbahul.dto.PrayerTimeResponse;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Service
public class PrayerTimeService {

    private final WebClient webClient;

    public PrayerTimeService(WebClient.Builder webClientBuilder) {
        HttpClient httpClient = HttpClient.create().followRedirect(true);

        this.webClient = webClientBuilder
                .baseUrl("https://api.aladhan.com/v1/")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    public PrayerTimeResponse getPrayerTimes(String city, String country) {
        try {
            String currentDate = java.time.LocalDate.now()
                    .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            PrayerTimeResponse response = this.webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("timingsByCity/{date}")
                            .queryParam("city", city)
                            .queryParam("country", country)
                            .queryParam("method", 3)
                            .build(currentDate))
                    .retrieve()
                    .bodyToMono(PrayerTimeResponse.class)
                    .block();

            if (response != null && response.getData() != null && response.getData().getTimings() != null) {
                cleanTimings(response.getData().getTimings());
            }

            return response;
        } catch (Exception e) {
            System.err.println("Prayer API Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private void cleanTimings(PrayerTimeResponse.TimingsDTO timings) {
        timings.setFajr(stripTimezone(timings.getFajr()));
        timings.setSunrise(stripTimezone(timings.getSunrise()));
        timings.setDhuhr(stripTimezone(timings.getDhuhr()));
        timings.setAsr(stripTimezone(timings.getAsr()));
        timings.setMaghrib(stripTimezone(timings.getMaghrib()));
        timings.setIsha(stripTimezone(timings.getIsha()));
    }

    private String stripTimezone(String time) {
        if (time == null) return "00:00";
        return time.replaceAll("\\s*\\(.*\\)", "").trim();
    }
}