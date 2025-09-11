package com.example;

import com.example.annotation.Loggable;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Loggable
    public String hello() {
        return "Hello RESTEasy";
    }


    @GET
    @Path("/toto")
    @Produces(MediaType.TEXT_PLAIN)
    @Loggable
    public String toto() {
        return "Hello RESTEasy from toto";
    }
}
