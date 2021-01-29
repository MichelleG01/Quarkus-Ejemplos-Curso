package org.curso.reactive;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;

@RegisterRestClient(configKey="word-api")
public interface WorldClockServiceWitchReact {

    //aqui hacemos la llamada del otro servicio
    @GET
    @Path("/json/{where}/now") //hacemos el path especifico de la llamada
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<WorldClockWitchReact> getNow(@PathParam("where") String where); //Lo que vamos a devolver

    //con CompletionStage le estamos indicando al cliente que haga la llamada en otro Stage y haras algo cuando recibas
    //el resultado

    //generamos una interficie que se comunica con /api y cuando llame al metodo getNow se comunica con /api/json/cet/now
}
