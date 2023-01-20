package my.recommendationoftravel.domain.user;

import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
@Getter
public class UserDTO {

    @NotBlank(message = "입력해주세요")
    @Size(min = 6, max = 12, message = "6 ~ 12자리 입력해주세요")
    private String userId;
    @NotBlank(message = "입력해주세요")
    @Size(min = 6, max = 12, message = "6 ~ 12자리 입력해주세요")
    private String password;
    @NotBlank(message = "입력해주세요")
    @Size(min = 6, max = 12, message = "6 ~ 12자리 입력해주세요")
    private String password2;

    public UserDTO(String userId, String password, String password2) {
        this.userId = userId;
        this.password = password;
        this.password2 = password2;
    }
}
