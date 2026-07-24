package taqwa.uz.taqwa.service;

import lombok.RequiredArgsConstructor;
import taqwa.uz.taqwa.entity.Surah;
import taqwa.uz.taqwa.entity.Verse;
import taqwa.uz.taqwa.repository.SurahRepository;
import taqwa.uz.taqwa.repository.VerseRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuranService {

    private final VerseRepository verseRepository;
    private final SurahRepository surahRepository;

    public QuranService(VerseRepository verseRepository, SurahRepository surahRepository) {
        this.verseRepository = verseRepository;
        this.surahRepository = surahRepository;
    }
    public List<Verse> getVersesBySurahId(Integer surahId) {
        return verseRepository.findBySurahIdOrderByVerseNumberAsc(surahId);
    }

    public List<Verse> getSurahContent(Integer surahId) {
        return verseRepository.findBySurahIdOrderByVerseNumberAsc(surahId);
    }

    public List<Verse> getJuzContent(Integer juzNumber) {
        return verseRepository.findByJuzNumberOrderBySurahIdAscVerseNumberAsc(juzNumber);
    }

    public List<Verse> getPageContent(Integer pageNumber) {
        return verseRepository.findByPageNumberOrderByVerseNumberAsc(pageNumber);
    }

    public Verse getSpecificVerse(Integer surahId, Integer verseNumber) {
        return verseRepository.findBySurahIdAndVerseNumber(surahId, verseNumber);
    }
    // QuranService.java ichiga qo'shing
    public List<Surah> getAllSurahs() {
        return surahRepository.findAll(); // Buning uchun SurahRepository kerak
    }

    public Surah getSurahById(Integer id) {
        return surahRepository.findById(id).orElseThrow(() -> new RuntimeException("Sura topilmadi"));
    }
}