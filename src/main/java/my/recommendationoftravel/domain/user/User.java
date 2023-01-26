package my.recommendationoftravel.domain.user;

import lombok.Getter;

import javax.persistence.*;

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
}
