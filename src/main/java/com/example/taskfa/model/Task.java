package com.example.taskfa.model;

import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;

public class Task {
    private int taskId;
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private Status STATUS;
    private int UserId;
    private SimpleStringProperty l_name;

    public Task(int taskId, String title, String description, Status STATUS, int UserId) {
        this.taskId = taskId;
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.STATUS = STATUS;
        this.UserId = UserId;
    }

    public Task(String title, String description, Status STATUS, int UserId) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.STATUS = STATUS;
        this.UserId = UserId;
    }

    public Task(String title, String description) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
    }

    public Task(String title, String description, String l_name) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.STATUS = Status.NOT_STARTED;
        this.l_name = new SimpleStringProperty(l_name);
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title.get();
    }

    public String getDescription() {
        return description.get();
    }

    public Status getSTATUS() {
        return STATUS;
    }

    public int getUserId() {
        return UserId;
    }

    public String getL_name() {
        return l_name.get();
    }
}
