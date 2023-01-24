package my.recommendationoftravel.controller;

import my.recommendationoftravel.domain.user.Role;
import my.recommendationoftravel.domain.user.User;
import my.recommendationoftravel.domain.user.UserDTO;
import my.recommendationoftravel.service.UserService;
import my.recommendationoftravel.util.AlertException;
import my.recommendationoftravel.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.http.HttpRequest;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "user/login";
    }

    @GetMapping("/login")
    public String login(String userId, String password, HttpSession session){
        User user = userService.checkLogin(userId, password);
        session.setAttribute("user",user);
        if(user.getRole().equals(Role.ADMIN)){
            return "redirect:/admin";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }


    @GetMapping("/signupPage")
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

        return "redirect:/loginPage";
    }

}
