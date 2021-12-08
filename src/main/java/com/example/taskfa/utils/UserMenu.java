package com.example.taskfa.utils;



public class UserMenu implements Menu{

    @Override
    public String showProjects() {
        return "/views/projectView.fxml";
    }
    @Override
    public String showOverView() {
        return "/views/OverView.fxml";
    }

    @Override
    public String showResources() {
        return "/views/Ressource.fxml";
    }

    @Override
    public String showTask() {
        return "/views/TASK.fxml";
    }

    @Override
    public String showVcsView() {
        return "/views/vcsView.fxml";
    }

    @Override
    public String showSideBar() {
        return "/views/SideBarView.fxml";
    }

}
