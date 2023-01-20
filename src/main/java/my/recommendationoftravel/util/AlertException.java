package my.recommendationoftravel.util;

import lombok.Getter;

@Getter

public class AlertException extends RuntimeException{
    private ErrorMessage errorMessage;

    public AlertException(ErrorMessage duplicateUserid) {
        this.errorMessage = duplicateUserid;
    }
}
