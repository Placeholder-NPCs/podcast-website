package show.placeholdernpcs;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import show.placeholdernpcs.model.Configuration;
import show.placeholdernpcs.service.template.TemplateService;

public class Application {
  public static void main(final String[] args) throws Exception {
    System.out.println("Running Application");

    System.out.println("Processing Template Pages");
    listResourceFiles("templates/pages").stream()
        .peek(p -> System.out.println("- " + p))
        .filter(p -> p.endsWith(".html"))
        .map(p -> p.substring(0, p.length() - 5))
        .forEach(Application::processTemplate);
  }

  private static void processTemplate(final String withTemplateName) {
    try {
      final String html = TemplateService.process("pages/" + withTemplateName);
      Path file = Paths.get("./deploy/" + withTemplateName + ".html");
      Files.writeString(file, html);
    } catch (IOException e) {
      System.err.println("An error occurred while writing to the file: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

  public static List<String> listResourceFiles(String resourcePath) throws Exception {
    URL resourceUrl = Application.class.getClassLoader().getResource(resourcePath);
    if (resourceUrl == null) {
      throw new IllegalArgumentException("Resource path not found: " + resourcePath);
    }

    if ("file".equals(resourceUrl.getProtocol())) {
      Path path = Path.of(resourceUrl.toURI());
      try (Stream<Path> files = Files.walk(path)) {
        return files
            .filter(Files::isRegularFile)
            .map(p -> path.relativize(p).toString())
            .collect(Collectors.toList());
      }
    } else {
      // Handle JARs or other protocols
      throw new UnsupportedOperationException(
          "Listing resources from non-file URLs is not directly supported by this method.");
    }
  }

  public static Configuration getConfiguration() {
    return Configuration.builder().urlPathPrefix(System.getenv("URL_PATH_PREFIX")).build();
  }
}
