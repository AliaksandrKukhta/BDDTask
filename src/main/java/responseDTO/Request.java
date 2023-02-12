package responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Request {
    @JsonProperty("type")
    public String type;
    @JsonProperty("query")
    public String query;
    @JsonProperty("language")
    public String language;
    @JsonProperty("unit")
    public String unit;
}
