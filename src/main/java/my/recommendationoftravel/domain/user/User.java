package my.recommendationoftravel.domain.user;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Entity
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERS_ID")
    private Long id;

    private String userId;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User userDtoToEntity(UserDTO userDTO){
        User user = new User();
        user.userId = userDTO.getUserId();
        user.password = userDTO.getPassword();
        user.role = Role.USER;
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userId, user.userId) && Objects.equals(password, user.password) && role == user.role;
    }

}
