package com.example.taskfa.controllers.tasks.admin;

import com.example.taskfa.controllers.tasks.TaskStatus;

public class TaskItemModel {
    private int taskid;
    private int userid;
    private String firstName;
    private String lastName;
    private int projectid;
    private String task;
    private TaskStatus taskStatus;

    @Override
    public String toString() {
        return "TaskItemModelController{" +
                "taskid=" + taskid +
                ", userid=" + userid +
                ", projectid=" + projectid +
                ", task='" + task + '\'' +
                ", taskStatus=" + taskStatus +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TaskItemModel(String firstName, String lastName, String task, TaskStatus taskStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.task = task;
        this.taskStatus = taskStatus;
    }
    public TaskItemModel() {

    }
    public TaskItemModel(int taskid, int userid, int projectid, String task, TaskStatus taskStatus) {
        this.taskid = taskid;
        this.userid = userid;
        this.projectid = projectid;
        this.task = task;
        this.taskStatus = taskStatus;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
