module com.example.taskfa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    // requires eu.hansolo.tilesfx;

    opens com.example.taskfa to javafx.fxml;
    exports com.example.taskfa;
    exports com.example.taskfa.controllers;
    opens com.example.taskfa.controllers to javafx.fxml;
}