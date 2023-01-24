package my.recommendationoftravel.controller;


import my.recommendationoftravel.domain.RequestAviationDTO;
import my.recommendationoftravel.domain.user.Role;
import my.recommendationoftravel.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

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


    @GetMapping("/admin")
    public String adminPage(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user.getRole().equals(Role.ADMIN)){
            return "admin/admin";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("/board")
    public String boardPage(){
        return "/board/userBoard";
    }
}
