package com.br.mt.task.model;

import java.time.LocalDate;

public class TaskDTO {

    private String title;
    private String description;
    private LocalDate dueDate;
    private Boolean completed;

    public Task toTask(String title, String description, LocalDate dueDate, Boolean completed) {
        return new Task(title, description, dueDate, completed);
    }
}
