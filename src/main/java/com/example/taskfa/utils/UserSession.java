package com.example.taskfa.utils;

import com.example.taskfa.model.User;

public class UserSession {
    private static User currentUser;

    public static void setCurrentUser(User user){
        currentUser = user;


    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
