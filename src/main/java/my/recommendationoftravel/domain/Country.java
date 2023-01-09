package my.recommendationoftravel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    private String region;
    private String country;
    private int arrFlight;
    private int depFlight;
    private int flights;

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

    public int getFlights() {
        return flights;
    }
}
