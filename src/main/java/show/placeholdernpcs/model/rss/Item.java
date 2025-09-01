package show.placeholdernpcs.model.rss;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import lombok.Builder;

@Builder(builderClassName = "Builder", toBuilder = true, setterPrefix = "with")
@JsonDeserialize(builder = Item.Builder.class)
public record Item(
    @JsonProperty("title") String title,
    @JsonProperty("description") String description,
    @JsonProperty("guid") String guid,
    @JsonProperty("pubDate") ZonedDateTime publicationDate,
    @JsonProperty("link") URL link,
    @JacksonXmlProperty(namespace = "itunes", localName = "image") Image itunesImage,
    @JacksonXmlProperty(namespace = "itunes", localName = "season") int itunesSeason,
    @JacksonXmlProperty(namespace = "itunes", localName = "episode") int itunesEpisode,
    @JacksonXmlProperty(namespace = "itunes", localName = "episodeType") String itunesEpisodeType) {

  public boolean isFullEpisode() {
    return Optional.ofNullable(this.itunesEpisodeType)
        .filter(itunesEpisodeType -> itunesEpisodeType.equalsIgnoreCase("full"))
        .isPresent();
  }

  public static class Builder {

    public Builder withPublicationDate(final ZonedDateTime publicationDate) {
      this.publicationDate = publicationDate;
      return this;
    }

    @JsonProperty("pubDate")
    public Builder withPublicationDate(final String publicationDate) {
      return this.withPublicationDate(
          ZonedDateTime.parse(publicationDate, DateTimeFormatter.RFC_1123_DATE_TIME));
    }
  }
}
