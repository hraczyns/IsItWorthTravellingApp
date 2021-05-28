package com.hraczynski.isitworthtravellingapp.exceptions;

public class EmptyWebResultException extends RuntimeException{
    public EmptyWebResultException() {
        super("Searched query has no web results.");
    }
}
