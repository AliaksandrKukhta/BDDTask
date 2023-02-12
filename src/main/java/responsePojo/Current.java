package responsePojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Current{
    @JsonProperty("observation_time")
    public String observationTime;
    @JsonProperty("temperature")
    public int temperature;
    @JsonProperty("weather_code")
    public int weatherCode;
    @JsonProperty("weather_icons")
    public ArrayList<String> weatherIcons;
    @JsonProperty("weather_descriptions")
    public ArrayList<String> weatherDescriptions;
    @JsonProperty("wind_speed")
    public int windSpeed;
    @JsonProperty("wind_degree")
    public int windDegree;
    @JsonProperty("wind_dir")
    public String windDir;
    @JsonProperty("pressure")
    public int pressure;
    @JsonProperty("precip")
    public int preCip;
    @JsonProperty("humidity")
    public int humidity;
    @JsonProperty("cloudcover")
    public int cloudCover;
    @JsonProperty("feelslike")
    public int feelsLike;
    @JsonProperty("uv_index")
    public int uvIndex;
    @JsonProperty("visibility")
    public int visibility;
    @JsonProperty("is_day")
    public String isDay;
}
