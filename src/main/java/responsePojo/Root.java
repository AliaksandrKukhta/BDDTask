package responsePojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Root {
    @JsonProperty("request")
    public Request request;
    @JsonProperty("location")
    public Location location;
    @JsonProperty("current")
    public Current current;
}
