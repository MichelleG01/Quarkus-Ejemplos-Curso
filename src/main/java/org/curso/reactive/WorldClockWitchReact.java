package org.curso.reactive;

public class WorldClockWitchReact {

    //este es una parte de lo que recibimos del servicio

    private String currentDateTime;

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}
