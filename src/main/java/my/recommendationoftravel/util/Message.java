package my.recommendationoftravel.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Message {

    String message = "";
    String href = "";

    public Message(String message, String href) {
        this.message = message;
        this.href = href;
    }
}
