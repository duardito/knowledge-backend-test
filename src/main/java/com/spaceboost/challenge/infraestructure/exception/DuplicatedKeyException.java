package com.spaceboost.challenge.infraestructure.exception;

public class DuplicatedKeyException extends RuntimeException {

    public DuplicatedKeyException(String message){
        super(message);
    }
}
