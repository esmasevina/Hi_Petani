package com.tugas_akhir;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("berandaView.fxml")));
        scene = new Scene(root, 1400, 1000);

        String css = Objects.requireNonNull(getClass().getResource("style/style.css")).toExternalForm();
        scene.getStylesheets().add(css);


        stage.setResizable(false);
        stage.setTitle("Hi-Petani");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}