package my.recommendationoftravel.controller;

import my.recommendationoftravel.domain.Country;
import my.recommendationoftravel.domain.RequestDateDTO;
import my.recommendationoftravel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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
    public String countryListPage(@Valid RequestDateDTO requestDateDTO, BindingResult bindingResult, Model model)
            throws IOException, InterruptedException {
        if(bindingResult.hasErrors()){
            return "aviation/aviation";
        }
        List<Country> countries = countryService.requestCountryApi(requestDateDTO);
        model.addAttribute("countryList", countries);
        return "aviation/countryList";
    }

}
