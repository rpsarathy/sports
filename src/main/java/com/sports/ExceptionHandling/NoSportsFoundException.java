package com.sports.ExceptionHandling;

public class NoSportsFoundException extends RuntimeException{
    public NoSportsFoundException(String message){
        super(message);
    }
}