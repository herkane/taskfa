package com.example.taskfa.controllers.project;

import java.util.Date;

public class InvitationModelTable {
    private int userId;
    private int projectId;
    private String title;
    private int membersNum;
    private String status;
    private String projectOwner;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMembersNum() {
        return membersNum;
    }

    public void setMembersNum(int membersNum) {
        this.membersNum = membersNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

    @Override
    public String toString() {
        return "InvitationModelTable{" +
                "userId=" + userId +
                ", projectId=" + projectId +
                ", title='" + title + '\'' +
                ", membersNum=" + membersNum +
                ", status='" + status + '\'' +
                ", projectOwner='" + projectOwner + '\'' +
                '}';
    }
}
