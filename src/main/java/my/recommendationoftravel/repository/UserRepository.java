package my.recommendationoftravel.repository;

import my.recommendationoftravel.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface UserRepository extends JpaRepository<User, Long> {
}
