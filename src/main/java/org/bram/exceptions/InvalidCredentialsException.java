package org.bram.exceptions;

public class InvalidCredentialsException extends DetailsAlreadyInUseException{
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
