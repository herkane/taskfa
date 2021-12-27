package com.example.taskfa.model;

import com.example.taskfa.utils.AdminMenu;
import com.example.taskfa.utils.Menu;
import com.example.taskfa.utils.UserMenu;
import javafx.scene.image.Image;

public class User {


    private int idUser;
    private String firstName;
    private String lastName;
    private String status;
    private Image image;
    private boolean admin;
    Menu menu = null;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public User(String firstName, String lastName, int idUser, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idUser = idUser;
        this.admin = admin;
        menu = new UserMenu();
    }

    public User() {

    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
        if (admin){
            menu = new AdminMenu();
        } else  {
            menu = new UserMenu();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public boolean authenticate(String email, String password) {
        return password.equals("");
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
