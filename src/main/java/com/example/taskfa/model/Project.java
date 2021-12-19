package com.example.taskfa.model;

import java.util.Date;

public class Project {
    private int projectId;
    private String title;
    private Date createdDate;
    private int membersNum;
    private Date nextMeeting;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getMembersNum() {
        return membersNum;
    }

    public void setMembersNum(int membersNum) {
        this.membersNum = membersNum;
    }

    public Date getNextMeeting() {
        return nextMeeting;
    }

    public void setNextMeeting(Date nextMeeting) {
        this.nextMeeting = nextMeeting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return this.title+ " " +this.projectId+" "+this.membersNum+" "+this.nextMeeting+" "+this.createdDate;
    }
}
