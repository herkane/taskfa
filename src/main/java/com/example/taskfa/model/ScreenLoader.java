package com.example.taskfa.model;

import com.example.taskfa.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

public class ScreenLoader {
    private Pane view;

    public Pane getPage(String fileName) {
        try {
            URL fileUrl = Main.class.getResource("/views/" + fileName + ".fxml");
            view = FXMLLoader.load(fileUrl);
        } catch (Exception e) {
            System.out.println("No page " + fileName + " please check FxmlLoader.");
            e.printStackTrace();
        }
        return view;
    }
}
