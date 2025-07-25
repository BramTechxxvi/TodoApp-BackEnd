package org.bram.data.repositories;

import org.bram.data.models.Task;
import org.bram.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findAllByUser(User user);
}
