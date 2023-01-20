package my.recommendationoftravel.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String password;

    public User userDtoToEntity(UserDTO userDTO){
        User user = new User();
        user.userId = userDTO.getUserId();
        user.password = userDTO.getPassword();
        return user;
    }
}
