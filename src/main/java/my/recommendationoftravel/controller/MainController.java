package my.recommendationoftravel.controller;


import my.recommendationoftravel.domain.RequestAviationDTO;
import my.recommendationoftravel.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

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
