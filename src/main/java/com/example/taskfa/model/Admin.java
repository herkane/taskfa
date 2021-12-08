package com.example.taskfa.model;

import com.example.taskfa.utils.AdminMenu;
import com.example.taskfa.utils.Menu;

public class Admin extends User{

    @Override
    public Menu getMenu() {
        return new AdminMenu();
    }
}
