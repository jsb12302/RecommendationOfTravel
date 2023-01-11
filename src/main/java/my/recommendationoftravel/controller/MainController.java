package my.recommendationoftravel.controller;


import my.recommendationoftravel.domain.RequestAviationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    @GetMapping("/aviationByCountry")
    public String aviationPage(RequestAviationDTO requestAviationDTO){
        return "aviation/aviation";
    }

}
