package responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Error {
    @JsonProperty("code")
    public int code;
    @JsonProperty("type")
    public String type;
    @JsonProperty("info")
    public String info;
}
