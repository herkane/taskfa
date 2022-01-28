package com.example.taskfa.model;

import java.util.Date;

public class Message {
    private User sender;
    private String message;
    private String date_sent;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(String date_sent) {
        this.date_sent = date_sent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender.toString() +
                ", message='" + message + '\'' +
                ", date_sent='" + date_sent + '\'' +
                '}';
    }
}
