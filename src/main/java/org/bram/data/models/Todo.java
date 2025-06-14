package org.bram.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "Tasks")
public class Todo {

    @Id
    private String id;
    private String title;
    private String description;
    private TodoStatus status = TodoStatus.PENDING;
    private LocalDate dueDate;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    @DBRef
    private User user;
}
