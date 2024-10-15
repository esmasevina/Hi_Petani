package com.tugas_akhir.collections.ui;

import com.tugas_akhir.AdminApp;
import com.tugas_akhir.config.Getter;
import com.tugas_akhir.config.Setter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CustomKategoriPane extends Pane {
    private Label kategori;
    private ImageView edit,remove;
    private Pane groupKategori;
    private int layoutY = 5;

    private Stage thisStage;

    public CustomKategoriPane(){
        String[][] rawKategori = Getter.getAllKategori();
        warningAlert("Harap berhati-hati saat menekan icon edit dan remove!");
        for (String [] kategor:rawKategori) {
            kategori = new Label(capitalize(kategor[1]));
        edit = new ImageView(new Image(AdminApp.class.getResource("icons/edit.png").toExternalForm()));
        remove = new ImageView(new Image(AdminApp.class.getResource("icons/tong.png").toExternalForm()));

        //grouping
        groupKategori = new Pane();
        groupKategori.getChildren().add(kategori);
        groupKategori.getChildren().add(edit);
        groupKategori.getChildren().add(remove);

        // custom component
        groupKategori.setPrefSize(320,40);
        groupKategori.getStyleClass().add("border");
        groupKategori.setLayoutY(layoutY);

        kategori.getStyleClass().add("fontStyle");
        kategori.setLayoutY(6);
        kategori.setLayoutX(10);

        edit.setFitWidth(25);
        edit.setFitHeight(25);
        edit.setLayoutX(230);
        edit.setLayoutY(7);
        edit.getStyleClass().add("animate");

        remove.setFitWidth(25);
        remove.setFitHeight(25);
        remove.setLayoutX(275);
        remove.setLayoutY(7);
        remove.getStyleClass().add("animate");

        this.getChildren().add(groupKategori);
        layoutY+=50;
        this.setPrefSize(320,layoutY + 20);
        }
    }

    public void modalAlert(String msg){
        Alert information;
        information = new Alert(Alert.AlertType.INFORMATION);
        information.setHeaderText(msg);
        information.showAndWait();
    }

    public void warningAlert(String msg){
        Alert information;
        information = new Alert(Alert.AlertType.WARNING);
        information.setHeaderText(msg);
        information.showAndWait();
    }

    private String capitalize(String s){
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public void setHeightParent(double h){
        ((Pane) this.getParent()).setPrefSize(320,h + layoutY);
    }
}


