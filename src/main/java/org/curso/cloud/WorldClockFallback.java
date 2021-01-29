package org.curso.cloud;

import org.curso.restclient.WorldClock;
import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;

//Este sera el Fallback si el servicio externo no responde, por ejem pudieramos proveer otro servicio (un backup)

public class WorldClockFallback implements FallbackHandler<WorldClock> {
    @Override
    public WorldClock handle(ExecutionContext executionContext) {
        WorldClock worldClock = new WorldClock();
        worldClock.setCurrentDateTime("No time");
        return worldClock;
    }
}
