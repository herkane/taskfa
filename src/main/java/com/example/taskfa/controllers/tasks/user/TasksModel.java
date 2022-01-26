package com.example.taskfa.controllers.tasks.user;

import com.example.taskfa.controllers.tasks.TaskStatus;

public class TasksModel {
    private String title;
    private Boolean completed;
    private TaskStatus status;

    public TasksModel(String title, boolean completed,TaskStatus status) {
        this.title = title;
        this.completed = completed;
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
