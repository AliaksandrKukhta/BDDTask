package responsePojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Root {
    @JsonProperty("request")
    public Request request;
    @JsonProperty("location")
    public Location location;
    @JsonProperty("current")
    public Current current;
}
