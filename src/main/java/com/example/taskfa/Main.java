package com.example.taskfa;

import com.example.taskfa.utils.IDandUsers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/SignIn.fxml"));
        IDandUsers session = new IDandUsers();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles/style.css").toExternalForm());
        Font.loadFont(getClass().getResource("fonts/Changa-Bold.ttf").toExternalForm(),20);
        Font.loadFont(getClass().getResource("fonts/Changa-VariableFont_wght.ttf").toExternalForm(),20);
        Font.loadFont(getClass().getResource("fonts/Changa-Regular.ttf").toExternalForm(),20);
        //Customized parameters
        stage.setFullScreenExitHint("");
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        //End of customized parameters
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}