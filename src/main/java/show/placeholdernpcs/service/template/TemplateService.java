package show.placeholdernpcs.service.template;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import lombok.NonNull;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public record TemplateService(ITemplateEngine templateEngine) {
  public TemplateService {
    Objects.requireNonNull(templateEngine);
  }

  private static ITemplateEngine buildTemplateEngine() {
    final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

    //  HTML is the default mode, but we will set it anyway for better understanding of code
    templateResolver.setTemplateMode(TemplateMode.HTML);

    // This will convert "home" to "/src/main/java/resources/templates/home.html"
    templateResolver.setPrefix("/templates/");
    templateResolver.setSuffix(".html");

    // Set template cache TTL to 1 hour.
    // If not set, entries would live in cache until expelled by LRU
    templateResolver.setCacheTTLMs(3600000L);
    templateResolver.setCacheable(true);

    final TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    return templateEngine;
  }

  private static final TemplateService INSTANCE = new TemplateService(buildTemplateEngine());

  public static String process(
      @NonNull final String withTemplateName, @NonNull final IContext withContext) {
    return INSTANCE.templateEngine.process(withTemplateName, withContext);
  }

  public static String process(
      @NonNull final String withTemplateName, @NonNull final Map<String, Object> withVariables) {
    return process(withTemplateName, new Context(Locale.ENGLISH, withVariables));
  }

  public static String process(@NonNull final String withTemplateName) {
    return process(withTemplateName, Collections.emptyMap());
  }
}
