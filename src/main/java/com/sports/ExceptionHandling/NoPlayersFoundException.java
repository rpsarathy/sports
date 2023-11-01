package com.sports.ExceptionHandling;

public class NoPlayersFoundException extends RuntimeException{
    public NoPlayersFoundException(String message){
        super(message);
    }
}