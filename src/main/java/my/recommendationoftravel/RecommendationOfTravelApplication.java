package my.recommendationoftravel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.servlet.http.HttpSession;

@SpringBootApplication
@EnableJpaAuditing
public class RecommendationOfTravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecommendationOfTravelApplication.class, args);
    }
}
