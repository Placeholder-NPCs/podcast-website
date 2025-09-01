package show.placeholdernpcs.model.rss;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.net.URL;
import lombok.Builder;

@Builder(builderClassName = "Builder", toBuilder = true, setterPrefix = "with")
@JsonDeserialize(builder = Item.Builder.class)
public record Item(
    @JsonProperty("title") String title,
    @JsonProperty("description") String description,
    @JsonProperty("link") URL link) {}
