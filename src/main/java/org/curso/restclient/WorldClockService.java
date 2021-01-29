package org.curso.restclient;

import org.curso.cloud.WorldClockFallback;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
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
    //@Retry(maxRetries = 2, delay = 1, delayUnit = ChronoUnit.SECONDS) //Si queremos definir cuantos intentos hacer
    @Retry(maxRetries = 2)
    @Fallback(WorldClockFallback.class)
    WorldClock getNow(); //Lo que vamos a devolver

    //generamos una interficie que se comunica con /api y cuando llame al metodo getNow se comunica con /api/json/cet/now

    //CircuitBreaker es otra opcion para la tolerancia a fallos
    //@CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.75, delay = 1000)
}
