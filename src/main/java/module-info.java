module com.example.taskfa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;

    // requires eu.hansolo.tilesfx;

    opens com.example.taskfa to javafx.fxml;
    exports com.example.taskfa;
    exports com.example.taskfa.controllers;
    opens com.example.taskfa.controllers to javafx.fxml;
    exports com.example.taskfa.controllers.chat;
    opens com.example.taskfa.controllers.chat to javafx.fxml;
    exports com.example.taskfa.controllers.project;
    opens com.example.taskfa.controllers.project to javafx.fxml;
    exports com.example.taskfa.controllers.vcs;
    opens com.example.taskfa.controllers.vcs to javafx.fxml;
    exports com.example.taskfa.controllers.sideBar;
    opens com.example.taskfa.controllers.sideBar to javafx.fxml;
    exports com.example.taskfa.utils;
    opens com.example.taskfa.utils to javafx.fxml;
    opens com.example.taskfa.model;
    opens com.example.taskfa.controllers.tasks.user to javafx.fxml;
    exports com.example.taskfa.controllers.tasks.user;
    exports com.example.taskfa.modelDao;
    opens com.example.taskfa.modelDao to javafx.fxml;
    opens com.example.taskfa.controllers.tasks.admin to javafx.fxml;
    exports com.example.taskfa.controllers.tasks.admin;

}