package no.kantega.cloud;

import no.kantega.cloud.services.UserService;
import no.kantega.cloud.domain.User;
import no.kantega.cloud.util.Responses;
import no.kantega.cloud.util.ValidationUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    public List<User> get() {
        return userService.getUsers();
    }

    @GET()
    @Path("/{id}")
    public Response getById(@PathParam("id") String id) {
        if (!ValidationUtils.isValidUuid(id)) {
            return Responses.badRequest("Invalid ID");
        }

        return userService.getUserById(UUID.fromString(id))
            .map(entity -> Response.ok(entity).build())
            .orElse(Responses.notFound());
    }

    @POST
    public Response addUser(User user) {
        userService.addUser(user);
        return Response.created(URI.create("/api/users/" + user.getId()))
            .entity(user)
            .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        if (!ValidationUtils.isValidUuid(id)) {
            return Responses.badRequest("Invalid ID");
        }
        if (userService.deleteUser(UUID.fromString(id))) {
            return Response.noContent().build();
        }
        return Responses.notFound();
    }
}
