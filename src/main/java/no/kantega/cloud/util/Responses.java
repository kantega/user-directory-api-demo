package no.kantega.cloud.util;

import javax.ws.rs.core.Response;
import java.util.Collections;

public class Responses {
    private Responses() {
        throw new IllegalAccessError("Utility class");
    }

    public static Response badRequest(String errorMessage) {
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(Collections.singletonMap("error", errorMessage))
            .build();
    }

    public static Response notFound() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
