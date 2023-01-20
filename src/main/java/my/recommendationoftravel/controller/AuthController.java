package my.recommendationoftravel.controller;

import my.recommendationoftravel.domain.user.UserDTO;
import my.recommendationoftravel.service.UserService;
import my.recommendationoftravel.util.AlertException;
import my.recommendationoftravel.util.ErrorMessage;
import my.recommendationoftravel.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String signUp(@Valid UserDTO userDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "user/signup";
        }
        if(!userDTO.getPassword().equals(userDTO.getPassword2())){
            throw new AlertException(ErrorMessage.NOT_MATCH_PASSWORD);
        }
        userService.registerUser(userDTO);

        return "redirect:/login";
    }

}
