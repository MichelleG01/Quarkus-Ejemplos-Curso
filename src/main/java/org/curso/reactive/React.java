package org.curso.reactive;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.reactivestreams.Publisher;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/react")
public class React {

    private AtomicInteger atomicInteger = new AtomicInteger();

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

}
