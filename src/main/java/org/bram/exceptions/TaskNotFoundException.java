package org.bram.exceptions;

public class TaskNotFoundException extends DetailsAlreadyInUseException {

    public TaskNotFoundException(String message) {
        super(message);
    }
}
