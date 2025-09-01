package show.placeholdernpcs.model.rss;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import lombok.Builder;

@Builder(builderClassName = "Builder", toBuilder = true, setterPrefix = "with")
@JsonDeserialize(builder = Channel.Builder.class)
public record Channel(
    @JsonProperty("title") String title,
    @JsonProperty("description") String description,
    @JsonProperty("link") URL link,
    @JsonProperty("generator") String generator,
    @JacksonXmlElementWrapper(useWrapping = false) List<Item> item) {

  public Item latestEpisode() {
    return item.stream()
        .filter(Item::isFullEpisode)
        .max(Comparator.comparing(Item::publicationDate))
        .orElse(null);
  }
}
