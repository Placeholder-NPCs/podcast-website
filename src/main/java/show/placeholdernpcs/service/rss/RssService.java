package show.placeholdernpcs.service.rss;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import show.placeholdernpcs.model.rss.RssFeed;

public record RssService() {

  public RssFeed fetchRssFeed(final URL withRssFeedUrl) throws IOException {
    final XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    try (final InputStream inputStream = withRssFeedUrl.openStream();
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
      String line;
      final StringBuilder xmlContent = new StringBuilder();
      while ((line = bufferedReader.readLine()) != null) {
        xmlContent.append(line);
      }
      final String xml = xmlContent.toString();
      System.out.println(xml);
      return xmlMapper.readValue(xml, RssFeed.class);
    }
  }
}
