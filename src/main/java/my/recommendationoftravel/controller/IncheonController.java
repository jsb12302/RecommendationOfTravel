package my.recommendationoftravel.controller;

import lombok.extern.slf4j.Slf4j;
import my.recommendationoftravel.domain.Country;
import my.recommendationoftravel.domain.Parking;
import my.recommendationoftravel.domain.RequestAviationDTO;
import my.recommendationoftravel.service.AviationService;
import my.recommendationoftravel.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
public class IncheonController {

    private final AviationService aviationService;
    private final ParkingService parkingService;

    @Autowired
    public IncheonController(AviationService aviationService, ParkingService parkingService) {
        this.aviationService = aviationService;
        this.parkingService = parkingService;
    }

    @GetMapping("/getAviation")
    public String countryListPage(@Valid RequestAviationDTO requestAviationDTO, BindingResult bindingResult, Model model)
            throws IOException, InterruptedException {
        log.info("Aviation API 호출");
        if(bindingResult.hasErrors()){
            return "aviation/aviation";
        }
        List<Country> countries = aviationService.requestCountryApi(requestAviationDTO);
        model.addAttribute("countryList", countries);
        model.addAttribute("requestDateDTO", requestAviationDTO);
        model.addAttribute("pageResponseDTO", aviationService.responsePage(requestAviationDTO));
        return "aviation/countryList";
    }

    @GetMapping("/getParking")
    public String ParkingInfoPage(Model model) throws IOException, InterruptedException {
        List<Parking> parkingList = parkingService.requestParkingApi();
        model.addAttribute("parkingList",parkingList);
        model.addAttribute("standardTime",parkingService.getParkingTime());
        return "parking/parkingList";
    }

}
