package org.curso.cloud;

import io.opentracing.Tracer;
import org.curso.restclient.WorldClock;
import org.curso.restclient.WorldClockService;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/metrica")
public class MetricasResource {

    private Long maxTemperature = 50L;

    @Inject
    @RestClient
    WorldClockService worldClockService;

    //para añadir información customizada del traceado que queramos realizar
    @Inject
    Tracer tracer;

    @GET
    @Path("/now")
    @Produces(MediaType.APPLICATION_JSON)
    //Si queremos mirar el tiempo medio de ejecucion de este metodo (medir cosas propias de nuestra app)
    @Timed(name = "CheckTimeGetNow", description = "Time to get current time", unit = MetricUnits.MILLISECONDS)
    @Counted(name = "Name of get time", description = "Numbers of calls") //cuantas veces se ha llamado
    public WorldClock getNow() {
        tracer.activeSpan().setBaggageItem("time", "now");
        return worldClockService.getNow();
    }
    //Para ver el resultado de la metrica, despues de hacer la peticion a http://localhost:8080/metrica/now
    // De forma mas resumida
    // En la url: http://localhost:8080/metrics/application
    //De una forma mas detallada
    // En la url: http://localhost:8080/metrics
    //ya cualquier plataforma de monitoreo puede ver estos resultados

    //Ahora si queremos devolver un dato en la metrica
    @Gauge(name = "Max temp", description = "Max temperature", unit = MetricUnits.NONE)
    public Long getMaxTemp() {
        return maxTemperature;
    }
    //esto nos sirve por ejm si un sistema de kafka viene y actualiza lo podemos mostrar

}
