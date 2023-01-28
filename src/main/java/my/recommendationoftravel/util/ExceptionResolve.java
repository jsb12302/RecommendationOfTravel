package my.recommendationoftravel.util;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionResolve {

    @ExceptionHandler
    public String handlerAlertException(AlertException ex, Model model){
        ErrorMessage errorMessage = ex.getErrorMessage();
        model.addAttribute("data", new Message(errorMessage.getMessage(),errorMessage.getHref()));
        return "user/message";
    }

}
