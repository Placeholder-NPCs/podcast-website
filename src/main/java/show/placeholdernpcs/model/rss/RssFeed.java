package show.placeholdernpcs.model.rss;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;

@Builder(builderClassName = "Builder", toBuilder = true, setterPrefix = "with")
@JacksonXmlRootElement(localName = "rss")
@JsonDeserialize(builder = RssFeed.Builder.class)
public record RssFeed(@JacksonXmlProperty(isAttribute = true) String version, Channel channel) {}
