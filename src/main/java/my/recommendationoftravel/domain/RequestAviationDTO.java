package my.recommendationoftravel.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
@Getter
@Setter
public class RequestAviationDTO {

    @NotBlank(message = "년월 입력은 필수 입니다.")
    @Size(min = 6, message = "올바르지 않은 입력입니다.")
    @Size(max = 6, message = "올바르지 않은 입력입니다.")
    String fromMonth;

    @NotNull(message = "년월 입력은 필수 입니다.")
    @Size(min = 6, message = "올바르지 않은 입력입니다.")
    @Size(max = 6, message = "올바르지 않은 입력입니다.")
    String toMonth;

    String order;
    int page;

}
