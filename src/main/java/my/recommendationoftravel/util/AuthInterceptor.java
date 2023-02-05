package my.recommendationoftravel.util;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        CheckSession checkSession = handlerMethod.getMethodAnnotation(CheckSession.class);

        if(checkSession == null){
            return true;
        }

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        System.out.println(user);
        if(user == null) {
            throw new AlertException(ErrorMessage.NOT_VALID_SESSION);
        }
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }
}
