package com.softserve.itacademy.component.task;

import com.softserve.itacademy.config.exception.NullEntityReferenceException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        if (task != null) {
            return taskRepository.save(task);
        }
        throw new NullEntityReferenceException("Task cannot be 'null'");
    }

    public Task readById(long id) {

        EntityNotFoundException exception = new EntityNotFoundException("Task with id " + id + " not found");
        logger.error(exception.getMessage(), exception);

        return taskRepository.findById(id).orElseThrow(
                () -> exception);
    }

    public Task update(Task task) {
        if (task != null) {
            readById(task.getId());
            return taskRepository.save(task);
        }
        throw new NullEntityReferenceException("Task cannot be 'null'");
    }

    public void delete(long id) {
        Task task = readById(id);
        taskRepository.delete(task);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public List<Task> getByTodoId(long todoId) {
        return taskRepository.getByTodoId(todoId);
    }
}