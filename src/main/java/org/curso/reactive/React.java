package org.curso.reactive;

import io.reactivex.Flowable;
import org.curso.restclient.WorldClock;
import org.curso.restclient.WorldClockService;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/react")
public class React {

    private AtomicInteger atomicInteger = new AtomicInteger();

    //inyectamos nuestro cliente rest en nuestro recurso
    @Inject
    @RestClient
    WorldClockServiceWitchReact worldClockServiceWitchReact;

    //Strem finito
    @GET
    @Path("/mensaje")
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> hello() {
        //generamos un stream static
        return ReactiveStreams.of("h", "e", "l", "l", "o")
                .map(String::toUpperCase)
                .limit(2) //si quiero limitar el stream
                .toList()
                .run() //ejecuta estas operaciones en cada item
                .thenApply(list -> list.toString()); //despues coge la lista y lo convierte en string
    }

    //stream "infinito"
    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS) //Asi nuestro cliente va a estar recibiendo cada update
    public Publisher<String> publish() {
        //generamos un string static
        return Flowable.interval(500, TimeUnit.MILLISECONDS)
                .map(s -> atomicInteger.getAndIncrement())
                .map(i -> Integer.toString(i));
    }

    //Cliente rest asincrono
    @GET
    @Path("/now")
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<List<WorldClockWitchReact>> getNow() {

        //De esta forma gastamos mas recursos ya que se hacen dos llamadas para responder
        /*WorldClockWitchReact cet = worldClockServiceWitchReact.getNow("cet");
        WorldClockWitchReact gmt = worldClockServiceWitchReact.getNow("gmt");
        return Arrays.asList(cet, gmt);*/

        //con CompletionStage vamos a gestionar mas conexiones
        //aqui hacemos las llamadas en paralelo
        CompletionStage<WorldClockWitchReact> cet = worldClockServiceWitchReact.getNow("cet");
        return cet.thenCombineAsync(
                worldClockServiceWitchReact.getNow("gmt"),
                (cetResult, gmtResult) -> Arrays.asList(cetResult, gmtResult)
        );
    }

}
