package my.recommendationoftravel.controller;

import my.recommendationoftravel.domain.Country;
import my.recommendationoftravel.domain.RequestAviationDTO;
import my.recommendationoftravel.service.CountryService;
import my.recommendationoftravel.service.page.PageResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class aviationController {

    private CountryService countryService;

    @Autowired
    public aviationController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/getAviation")
    public String countryListPage(@Valid RequestAviationDTO requestAviationDTO, BindingResult bindingResult, Model model)
            throws IOException, InterruptedException {
        if(bindingResult.hasErrors()){
            return "aviation/aviation";
        }
        List<Country> countries = countryService.requestCountryApi(requestAviationDTO);
        model.addAttribute("countryList", countries);
        model.addAttribute("requestDateDTO", requestAviationDTO);
        model.addAttribute("pageResponseDTO",countryService.responsePage(requestAviationDTO));
        return "aviation/countryList";
    }

}
