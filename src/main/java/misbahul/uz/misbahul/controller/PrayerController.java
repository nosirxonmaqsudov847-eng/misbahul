package misbahul.uz.misbahul.controller;

import misbahul.uz.misbahul.dto.PrayerTimeResponse;
import misbahul.uz.misbahul.service.PrayerTimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrayerController {

    private final PrayerTimeService prayerTimeService;

    public PrayerController(PrayerTimeService prayerTimeService) {
        this.prayerTimeService = prayerTimeService;
    }

    @GetMapping("/prayer-times")
    public String prayerPage(Model model,
            @RequestParam(defaultValue = "Tashkent") String city,
            @RequestParam(defaultValue = "Uzbekistan") String country) {
        model.addAttribute("prayerTimes", prayerTimeService.getPrayerTimes(city, country));
        model.addAttribute("city", city);
        model.addAttribute("currentPage", "prayer");
        return "prayer-times";
    }

    @GetMapping("/api/prayer")
    @ResponseBody
    public PrayerTimeResponse getPrayerTimesApi(@RequestParam(defaultValue = "Tashkent") String city,
            @RequestParam(defaultValue = "Uzbekistan") String country) {
        return prayerTimeService.getPrayerTimes(city, country);
    }
}
