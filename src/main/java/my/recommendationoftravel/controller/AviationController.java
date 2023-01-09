package my.recommendationoftravel.controller;

import my.recommendationoftravel.domain.Country;
import my.recommendationoftravel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class AviationController {

    private CountryService countryService;

    @Autowired
    public AviationController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/getAviation")
    public String countryListPage(@RequestParam String fromMonth, @RequestParam String toMonth, Model model) throws IOException, InterruptedException {
        List<Country> countries = countryService.requestCountryApi(fromMonth, toMonth);
        model.addAttribute("countryList", countries);
        return "aviation/countryList";
    }

}
