package misbahul.uz.misbahul.controller;

import misbahul.uz.misbahul.entity.Surah;
import misbahul.uz.misbahul.service.QuranService;
import misbahul.uz.misbahul.service.QuranSyncService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class QuranController {

    private final QuranService quranService;
    private final QuranSyncService quranSyncService;

    public QuranController(QuranService quranService, QuranSyncService quranSyncService) {
        this.quranService = quranService;
        this.quranSyncService = quranSyncService;
    }

    @GetMapping("/quran/sync")
    @ResponseBody
    public String syncQuran() {
        return quranSyncService.syncData();
    }

    @GetMapping("/quran")
    public String quranPage(Model model) {
        model.addAttribute("surahs", quranService.getAllSurahs());
        model.addAttribute("currentPage", "quran");
        return "quran";
    }

    @GetMapping("/surah/{id}")
    public String surahDetailPage(@PathVariable("id") Integer id, Model model) {
        try {
            quranSyncService.syncSurahVerses(id);
            var surah = quranService.getSurahById(id);
            var verses = quranService.getVersesBySurahId(id);

            // Group verses by page number
            java.util.Map<Integer, List<misbahul.uz.misbahul.entity.Verse>> versesByPage = new java.util.LinkedHashMap<>();
            for (var verse : verses) {
                versesByPage.computeIfAbsent(verse.getPageNumber(), k -> new java.util.ArrayList<>()).add(verse);
            }

            model.addAttribute("surah", surah);
            model.addAttribute("verses", verses);
            model.addAttribute("versesByPage", versesByPage);
            model.addAttribute("currentPage", "quran");
            model.addAttribute("allSurahs", quranService.getAllSurahs());

            // Navigation info
            if (id > 1) {
                model.addAttribute("prevSurah", quranService.getSurahById(id - 1));
            }
            if (id < 114) {
                model.addAttribute("nextSurah", quranService.getSurahById(id + 1));
            }
        } catch (Exception e) {
            return "redirect:/quran?error=surah_not_found";
        }
        return "surah-detail";
    }

    @GetMapping("/api/quran/surahs")
    @ResponseBody
    public List<Surah> getSurahsApi() {
        return quranService.getAllSurahs();
    }
}
