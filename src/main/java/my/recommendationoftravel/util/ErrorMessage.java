package my.recommendationoftravel.util;


import lombok.Getter;

@Getter
public enum ErrorMessage{

    NOT_MATCH_PASSWORD("두 비밀번호가 일치하지 않습니다.","/signup"),
    DUPLICATE_USERID("회원 아이디가 중복 되었습니다.", "/signup");


    private String message;
    private String href;

    ErrorMessage(String message, String href) {
        this.message = message;
        this.href = href;
    }
}
