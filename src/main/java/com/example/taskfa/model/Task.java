package com.example.taskfa.model;

public class Task {
    private int taskId;
    private String title;
    private String description;
    private Status STATUS;
    private int UserId;

    public Task(int taskId, String title, String description, Status STATUS, int UserId) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.STATUS = STATUS;
        this.UserId = UserId;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getSTATUS() {
        return STATUS;
    }

    public int getUserId() {
        return UserId;
    }
}
