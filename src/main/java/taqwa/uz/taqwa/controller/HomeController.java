package taqwa.uz.taqwa.controller;

import taqwa.uz.taqwa.entity.Surah;
import taqwa.uz.taqwa.service.QuranService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class HomeController {

    private final QuranService quranService;

    public HomeController(QuranService quranService) {
        this.quranService = quranService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("currentPage", "home");

        try {
            long surahCount = quranService.getAllSurahs().size();
            if (surahCount > 0) {
                // Har kuni bir xil, lekin ketma-ket tasodifiy sura
                int dayOfYear = LocalDate.now().getDayOfYear();
                int surahId = (dayOfYear % (int) surahCount) + 1;
                Surah dailySurah = quranService.getSurahById(surahId);
                model.addAttribute("dailySurah", dailySurah);
            }
        } catch (Exception e) {
            // Agar suralar yuklanmagan bo'lsa, default qoldirish
        }

        return "index";
    }
}