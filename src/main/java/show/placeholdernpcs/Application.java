package show.placeholdernpcs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import show.placeholdernpcs.service.template.TemplateService;

public class Application {
  public static void main(final String[] args) {
    System.out.println("Running Application");

    // Inline processing of the 404 page
    final String html = TemplateService.process("pages/404");
    try {
      Path file = Paths.get("./deploy/404.html");
      Files.writeString(file, html);
    } catch (IOException e) {
      System.err.println("An error occurred while writing to the file: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
