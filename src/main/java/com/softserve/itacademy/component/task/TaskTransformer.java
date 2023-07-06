package com.softserve.itacademy.component.task;

import com.softserve.itacademy.component.state.State;
import com.softserve.itacademy.component.todo.ToDo;
import org.springframework.stereotype.Component;

@Component
public class TaskTransformer {

    public TaskDto convertToDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getName(),
                task.getPriority().toString(),
                task.getTodo().getId(),
                task.getState().getId()
        );
    }

    public Task fillEntityFields(Task task, TaskDto taskDto, ToDo todo, State state) {
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setPriority(TaskPriority.valueOf(taskDto.getPriority()));
        task.setTodo(todo);
        task.setState(state);
        return task;
    }
}
