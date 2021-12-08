package com.example.taskfa.utils;

import com.example.taskfa.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class IDandUsers {
    static HashMap<String, User> loginInfo = new HashMap<String, User>();
    private static String currentUser;

    public IDandUsers() {
        User user;
        Date date = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("hh:mm");

        user = new User();
        user.setFirstName("Lena");
        user.setLastName("Prince");
        user.setStatus("Active");
        user.setImgSrc("/media/profile_pic_1.jfif");
        user.setIdUser(100);
        loginInfo.put(user.getFirstName()+' '+user.getLastName(), user);

        user = new User();
        user.setFirstName("Raphael");
        user.setLastName("Sanders");
        user.setStatus("Acive");
        user.setImgSrc("/media/profile_pic_6.jpg");
        user.setIdUser(101);
        loginInfo.put(user.getFirstName()+' '+user.getLastName(), user);
        user = new User();
        user.setFirstName("Mariah");
        user.setLastName("Walker");
        user.setStatus("Last seen 8min ago");
        user.setImgSrc("/media/profile_pic_2.jfif");
        user.setIdUser(103);
        loginInfo.put(user.getFirstName()+' '+user.getLastName(), user);
        user = new User();
        user.setFirstName("Donald");
        user.setLastName("Michael");
        user.setStatus("Last seen 1h ago");
        user.setImgSrc("/media/profile_pic_4.jfif");
        user.setIdUser(104);
        loginInfo.put(user.getFirstName()+' '+user.getLastName(), user);
    }

    public static HashMap getLoginInfo() {
        return loginInfo;
    }

    public static User getUserObject(String fullname) {
        return loginInfo.get(fullname);
    }

    public static void setCurrentUser(String fullname) {
        currentUser = fullname;
    }

    public static String getCurrentUser() {
        return currentUser;
    }
}
