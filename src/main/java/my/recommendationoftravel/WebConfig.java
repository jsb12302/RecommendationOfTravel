package my.recommendationoftravel;

import lombok.RequiredArgsConstructor;
import my.recommendationoftravel.util.HttpSessionArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final HttpSessionArgumentResolver httpSessionArgumentResolver;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(httpSessionArgumentResolver);
    }
}
