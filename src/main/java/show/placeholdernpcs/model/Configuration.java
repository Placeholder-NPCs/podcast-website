package show.placeholdernpcs.model;

import lombok.Builder;
import lombok.NonNull;

@Builder(builderClassName = "Builder", toBuilder = true)
public record Configuration(@NonNull String urlPathPrefix) {}
