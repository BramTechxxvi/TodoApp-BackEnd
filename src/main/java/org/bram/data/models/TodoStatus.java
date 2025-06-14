package org.bram.data.models;

public enum TodoStatus {

    COMPLETED("Completed"),
    IN_PROGRESS("In Progress..."),
    CANCELLED("Cancelled"),
    PENDING("Pending..."),;

    private final String status;

    TodoStatus(String status) {
        this.status = status;
    }


}
