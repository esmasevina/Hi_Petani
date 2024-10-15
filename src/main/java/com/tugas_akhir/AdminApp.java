package com.tugas_akhir;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(AdminApp.class.getResource("adminPanel.fxml"));

        Scene scene = new Scene(root, 350, 510);

        scene.getStylesheets().add(getClass().getResource("style/adminPanel.css").toExternalForm());

        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
