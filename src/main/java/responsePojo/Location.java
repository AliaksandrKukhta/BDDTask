package responsePojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Location {
    @JsonProperty("name")
    public String name;
    @JsonProperty("country")
    public String country;
    @JsonProperty("region")
    public String region;
    @JsonProperty("lat")
    public String lat;
    @JsonProperty("lon")
    public String lon;
    @JsonProperty("timezone_id")
    public String timezoneId;
    @JsonProperty("localtime")
    public String localtime;
    @JsonProperty("localtime_epoch")
    public int localtimeEpoch;
    @JsonProperty("utc_offset")
    public String utcOffset;

}
