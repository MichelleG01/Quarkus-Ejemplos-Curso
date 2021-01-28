package org.curso;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/prueba")
public class Getting {

    @Inject
    GettingService service;

    @GET
    @Path("/mensaje")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return service.toUpperCase();
    }

    //Para enviar un JSON. Utilizando la extencion resteasy-jsonb
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Beer jsonBeer() {
        return new Beer("Artesanal", 200);
    }

    //Si recibimos un JSON y lo vamos a descerializar
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBeer(Beer beer) {
        System.out.println(beer);
        System.out.println(beer.getName());
        return Response.ok().build();
    }
}