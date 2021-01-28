package org.curso.restclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey="word-api")
public interface WorldClockService {

    //aqui hacemos la llamada del otro servicio
    @GET
    @Path("/json/cet/now") //hacemos el path especifico de la llamada
    @Produces(MediaType.APPLICATION_JSON)
    WorldClock getNow(); //Lo que vamos a devolver

    //generamos una interficie que se comunica con /api y cuando llame al metodo getNow se comunica con /api/json/cet/now
}
