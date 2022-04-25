package com.electrogrid.PAF.controller;

import com.electrogrid.PAF.model.User;
import com.electrogrid.PAF.service.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;

/*
 *default Port : 8030
 *http://localhost:8030/user/*
 */
@Path("/user")
public class UserController {

    private User user;
    private final UserService userService = new UserService();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(HashMap<String, ?> userData) {
        String nic = (String) userData.get("nic");
        String name = (String) userData.get("name");
        String city = (String) userData.get("city");
        String number = (String) userData.get("number");
        user = new User(nic, name, city, number);

        return userService.addUser(user);
    }

    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(HashMap<String, ?> userData, @PathParam("userId") Integer userId) {
        String nic = (String) userData.get("nic");
        String name = (String) userData.get("name");
        String city = (String) userData.get("city");
        String number = (String) userData.get("number");
        user = new User(nic, name, city, number);
        user.setId(userId);

        return userService.updateUser(user);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return userService.getUsers();
    }

    @GET
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("userId") Integer userId) {
        return userService.getUserById(userId);
    }

    @DELETE
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("userId") Integer userId) {
        return userService.deleteUser(userId);
    }
}
