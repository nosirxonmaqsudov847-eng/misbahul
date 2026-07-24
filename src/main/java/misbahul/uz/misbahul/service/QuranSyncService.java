package misbahul.uz.misbahul.service;

import misbahul.uz.misbahul.entity.Surah;
import misbahul.uz.misbahul.entity.Verse;
import misbahul.uz.misbahul.repository.SurahRepository;
import misbahul.uz.misbahul.repository.VerseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class QuranSyncService {

    private final WebClient webClient;
    private final SurahRepository surahRepository;
    private final VerseRepository verseRepository;

    public QuranSyncService(WebClient.Builder webClientBuilder,
            SurahRepository surahRepository,
            VerseRepository verseRepository) {
        // Al-Baqara kabi katta suralar uchun xotira limitini 16MB gacha oshiramiz
        this.webClient = webClientBuilder
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .baseUrl("https://api.alquran.cloud/v1/")
                .build();
        this.surahRepository = surahRepository;
        this.verseRepository = verseRepository;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public String syncData() {
        if (surahRepository.count() > 0) {
            return "Ma'lumotlar allaqachon mavjud.";
        }

        try {
            Map<String, Object> response = webClient.get()
                    .uri("surah")
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(m -> (Map<String, Object>) m)
                    .block();

            if (response == null || !response.containsKey("data"))
                return "Xatolik: API'dan javob kelmadi.";

            List<Map<String, Object>> surahData = (List<Map<String, Object>>) response.get("data");

            for (Map<String, Object> s : surahData) {
                Surah surah = new Surah();
                Integer number = (Integer) s.get("number");
                if (number != null) {
                    surah.setId(number);
                }
                surah.setNameUz((String) s.get("englishName"));
                surah.setNameEn((String) s.get("englishName"));
                surah.setNameArabic((String) s.get("name"));
                surah.setTotalVerses((Integer) s.get("numberOfAyahs"));
                surahRepository.save(surah);
            }

            return "114 ta sura muvaffaqiyatli saqlandi.";
        } catch (Exception e) {
            return "Xatolik yuz berdi: " + e.getMessage();
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public void syncSurahVerses(Integer surahId) {
        if (verseRepository.existsBySurahId(surahId))
            return;

        try {
            // Editions: Uthmani, Uzbek, Russian, English, Tajweed
            Map<String, Object> response = webClient.get()
                    .uri("surah/{id}/editions/quran-uthmani,uz.sodik,ru.kuliev,en.sahih,quran-tajweed", surahId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(m -> (Map<String, Object>) m)
                    .block();

            if (response == null || !response.containsKey("data"))
                return;

            List<Map<String, Object>> editions = (List<Map<String, Object>>) response.get("data");
            Map<String, Object> arabicEdition = editions.get(0);
            Map<String, Object> uzbekEdition = editions.get(1);
            Map<String, Object> russianEdition = editions.get(2);
            Map<String, Object> englishEdition = editions.get(3);
            Map<String, Object> tajweedEdition = editions.get(4);

            List<Map<String, Object>> arabicAyahs = (List<Map<String, Object>>) arabicEdition.get("ayahs");
            List<Map<String, Object>> uzbekAyahs = (List<Map<String, Object>>) uzbekEdition.get("ayahs");
            List<Map<String, Object>> russianAyahs = (List<Map<String, Object>>) russianEdition.get("ayahs");
            List<Map<String, Object>> englishAyahs = (List<Map<String, Object>>) englishEdition.get("ayahs");
            List<Map<String, Object>> tajweedAyahs = (List<Map<String, Object>>) tajweedEdition.get("ayahs");

            Surah surah = surahRepository.findById(surahId).orElse(null);
            if (surah == null)
                return;

            for (int i = 0; i < arabicAyahs.size(); i++) {
                Map<String, Object> a = arabicAyahs.get(i);
                Map<String, Object> u = uzbekAyahs.get(i);
                Map<String, Object> r = russianAyahs.get(i);
                Map<String, Object> e = englishAyahs.get(i);
                Map<String, Object> t = tajweedAyahs.get(i);

                Verse verse = new Verse();
                verse.setSurah(surah);

                Integer juz = (Integer) a.get("juz");
                if (juz != null)
                    verse.setJuzNumber(juz);

                Integer page = (Integer) a.get("page");
                if (page != null)
                    verse.setPageNumber(page);

                Integer vNum = (Integer) a.get("numberInSurah");
                if (vNum != null)
                    verse.setVerseNumber(vNum);
                verse.setTextArabic((String) a.get("text"));
                verse.setTextUz((String) u.get("text"));
                verse.setTextRu((String) r.get("text"));
                verse.setTextEn((String) e.get("text"));
                verse.setTextTajweed((String) t.get("text"));

                Integer globalNumber = (Integer) a.get("number");
                verse.setAudioUrl("https://cdn.islamic.network/quran/audio/128/ar.alafasy/" + globalNumber + ".mp3");

                verseRepository.save(verse);
            }
        } catch (Exception e) {
            System.err.println("Verse sync error for surah " + surahId + ": " + e.getMessage());
        }
    }
}
