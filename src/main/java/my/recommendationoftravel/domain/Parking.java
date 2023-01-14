package my.recommendationoftravel.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class Parking {

    private String floor;
    private int parking;
    private int parkingArea;
    private String dateTime;
}
