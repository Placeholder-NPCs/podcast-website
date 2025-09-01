package show.placeholdernpcs.model.rss;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.net.URL;
import lombok.Builder;

@Builder(builderClassName = "Builder", toBuilder = true, setterPrefix = "with")
@JsonDeserialize(builder = Image.Builder.class)
public record Image(@JacksonXmlProperty(isAttribute = true) @JsonProperty("href") URL href) {}
