package no.kantega.cloud.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = User.UserBuilder.class)
public class User {
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String username;
    private String name;
    private String email;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserBuilder {
    }
}
