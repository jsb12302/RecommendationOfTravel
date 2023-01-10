package my.recommendationoftravel.controller;


import my.recommendationoftravel.domain.RequestDateDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    @GetMapping("/aviationByCountry")
    public String aviationPage(RequestDateDTO requestDateDTO){
        return "aviation/aviation";
    }
}
