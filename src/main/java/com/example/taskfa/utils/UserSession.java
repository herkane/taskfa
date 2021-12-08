package com.example.taskfa.utils;

public class UserSession {
    private static String firstName;
    private static String lastName;
    private static int IdUser;

    public UserSession(String firstName, String lastName, int idUser) {
        UserSession.firstName = firstName;
        UserSession.lastName = lastName;
        IdUser = idUser;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static int getIdUser() {
        return IdUser;
    }
}
