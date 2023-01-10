package my.recommendationoftravel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Country {

    private String region;
    private String country;
    private int arrFlight;
    private int depFlight;

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public int getArrFlight() {
        return arrFlight;
    }

    public int getDepFlight() {
        return depFlight;
    }
}
