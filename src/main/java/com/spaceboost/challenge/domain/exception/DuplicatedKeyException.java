package com.spaceboost.challenge.domain.exception;

public class DuplicatedKeyException extends RuntimeException {

    public DuplicatedKeyException(String message){
        super(message);
    }
}
