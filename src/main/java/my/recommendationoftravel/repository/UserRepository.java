package my.recommendationoftravel.repository;

import my.recommendationoftravel.domain.user.User;
import my.recommendationoftravel.domain.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.Optional;
import java.util.OptionalInt;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);

}
