package responsePojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorRoot {
    @JsonProperty("success")
    public boolean success;
    @JsonProperty("error")
    public Error error;
}
