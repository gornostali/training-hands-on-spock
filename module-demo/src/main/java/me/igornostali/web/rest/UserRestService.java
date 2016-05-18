package me.igornostali.web.rest;

import me.igornostali.data.UserRepository;
import me.igornostali.model.User;
import me.igornostali.service.UserService;
import me.igornostali.service.exception.AlreadyExistsException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1/users")
public class UserRestService {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserRestService(final UserService userService, final UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(final User user) {
        try {
            final User created = this.userService.register(user);

            return Response.ok(created).build();
        } catch (AlreadyExistsException e) {
            e.printStackTrace();

            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(user)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        final User[] users = this.userRepository.getAll();

        return Response.ok(users).build();
    }
}
