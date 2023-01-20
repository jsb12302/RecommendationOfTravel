package my.recommendationoftravel.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionResolve {

    @ExceptionHandler
    public ModelAndView handlerAlertException(AlertException ex){
        ModelAndView modelAndView = new ModelAndView();
        ErrorMessage errorMessage = ex.getErrorMessage();
        modelAndView.addObject("data", new Message(errorMessage.getMessage(),errorMessage.getHref()));
        modelAndView.setViewName("user/message");
        return modelAndView;
    }

}
