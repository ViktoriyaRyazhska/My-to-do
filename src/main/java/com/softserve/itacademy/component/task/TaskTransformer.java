package com.softserve.itacademy.component.task;

import com.softserve.itacademy.component.state.State;
import com.softserve.itacademy.component.todo.ToDo;

public class TaskTransformer {
    public static TaskDto convertToDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getName(),
                task.getPriority().toString(),
                task.getTodo().getId(),
                task.getState().getId()
        );
    }

    public static Task convertToEntity(TaskDto taskDto, ToDo todo, State state) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setPriority(TaskPriority.valueOf(taskDto.getPriority()));
        task.setTodo(todo);
        task.setState(state);
        return task;
    }
}
