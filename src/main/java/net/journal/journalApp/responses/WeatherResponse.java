package net.journal.journalApp.responses;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse {

    
    private Main main;
    private ArrayList<Weather> weather;

    @Data
    public static class Main {
        private double temp;

        @JsonProperty("feels_like")
        private double feelsLike;
    }

    @Data
    public static class Weather {
        private String description;
    }

}
