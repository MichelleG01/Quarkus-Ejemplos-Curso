package org.curso;

import org.curso.restclient.WorldClock;
import org.curso.restclient.WorldClockService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("/prueba")
public class Getting {

    @Inject
    GettingService service;

    Logger logger = Logger.getLogger(Getting.class);

    //inyectamos nuestro cliente rest en nuestro recurso
    @Inject
    @RestClient
    WorldClockService worldClockService;

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
        return new Beer("Artesanal", 200, LocalDate.parse("2022-01-01"));
    }

    //Si recibimos un JSON y lo vamos a descerializar
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBeer(@Valid Beer beer) {
        System.out.println(beer);
        System.out.println(beer.getName());
        logger.debug("Hello");
        return Response.ok().build();
    }

    //Consumimos el api de: http://worldclockapi.com/api/json/cet/now
    @GET
    @Path("/now")
    @Produces(MediaType.APPLICATION_JSON)
    public WorldClock getNow() {
        return worldClockService.getNow();
    }
}