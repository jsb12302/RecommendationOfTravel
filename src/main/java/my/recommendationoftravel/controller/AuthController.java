package my.recommendationoftravel.controller;

import my.recommendationoftravel.domain.user.UserDTO;
import my.recommendationoftravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "user/login";
    }

    @GetMapping("/signup")
    public String signUpPage(UserDTO userDTO){
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signUp(UserDTO userDTO){
        return "redirect:/login";
    }

}
