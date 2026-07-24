package taqwa.uz.taqwa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasbehController {

    @GetMapping("/tasbeh")
    public String tasbehPage(Model model) {
        model.addAttribute("currentPage", "tasbeh");
        return "tasbeh";
    }
}
