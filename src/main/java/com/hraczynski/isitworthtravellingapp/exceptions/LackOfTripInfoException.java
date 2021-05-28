package com.hraczynski.isitworthtravellingapp.exceptions;

public class LackOfTripInfoException extends RuntimeException{
    public LackOfTripInfoException() {
        super("The response does not contain trip information.");
    }
}
