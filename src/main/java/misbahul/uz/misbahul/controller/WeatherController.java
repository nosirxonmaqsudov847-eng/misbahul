package misbahul.uz.misbahul.controller;

import misbahul.uz.misbahul.dto.WeatherResponse;
import misbahul.uz.misbahul.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String weatherPage(Model model, @RequestParam(defaultValue = "Tashkent") String city) {
        model.addAttribute("weather", weatherService.getWeather(city));
        model.addAttribute("city", city);
        model.addAttribute("currentPage", "weather");
        return "weather";
    }

    @GetMapping("/api/weather")
    @ResponseBody
    public WeatherResponse getWeatherApi(@RequestParam(defaultValue = "Tashkent") String city) {
        return weatherService.getWeather(city);
    }
}
