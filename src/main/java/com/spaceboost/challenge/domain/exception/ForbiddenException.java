package com.spaceboost.challenge.domain.exception;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message){
        super(message);
    }
}
