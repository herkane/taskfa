package com.example.taskfa.utils;

public class AdminMenu implements Menu{
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
        return "/views/task.fxml";
    }

    @Override
    public String showVcsView() {
        return "/views/vcsViewAdmin.fxml";
    }

    @Override
    public String showSideBar() {
        return "/views/SideBarView.fxml";
    }

    @Override
    public String showChat() {
        return "/views/chatView.fxml";
    }


}
