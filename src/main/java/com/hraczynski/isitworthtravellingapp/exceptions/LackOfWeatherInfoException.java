package com.hraczynski.isitworthtravellingapp.exceptions;

public class LackOfWeatherInfoException extends RuntimeException {
    public LackOfWeatherInfoException() {
        super("The response does not contain weather information.");
    }
}
