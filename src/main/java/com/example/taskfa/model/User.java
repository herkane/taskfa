package com.example.taskfa.model;

import com.example.taskfa.utils.Menu;
import com.example.taskfa.utils.UserMenu;

public class User {


    private int idUser;
    private String firstName;
    private String lastName;
    private String status;
    private String imgSrc;

    public User(String firstName, String lastName, int idUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idUser = idUser;
    }

    public User() {

    }

    public Menu getMenu() {
        return new UserMenu();
    }

    public boolean authenticate(String email, String password) {
        return email.equals(password);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
