package org.bram.exceptions;

public class DetailsAlreadyInUseException extends InvalidCredentialsException{
    public DetailsAlreadyInUseException(String message) {
        super(message);
    }
}
