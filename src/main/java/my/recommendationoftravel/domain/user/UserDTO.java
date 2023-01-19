package my.recommendationoftravel.domain.user;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserDTO {

    private String userId;
    private String password;
    private String password2;

    public UserDTO(String userId, String password, String password2) {
        this.userId = userId;
        this.password = password;
        this.password2 = password2;
    }
}
