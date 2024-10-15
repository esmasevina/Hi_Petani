package com.tugas_akhir.controller;

import com.tugas_akhir.App;
import com.tugas_akhir.collections.User;
import com.tugas_akhir.config.Getter;
import com.tugas_akhir.config.Setter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class regisController {

    @FXML
    private Hyperlink login;

    @FXML
    private TextField username,namaLengkap,password,noHp;

    @FXML
    private Button signUp;

    private Stage stage;

    private ArrayList<User> allMember = Getter.getAllMember();

    public void initialize(){
        evHandler();
    }

    public void evHandler(){
        // deklarasi alert
        Alert infoUsername = new Alert(Alert.AlertType.INFORMATION);
        infoUsername.setTitle("Information");
        infoUsername.setHeaderText("Username sudah terdaftar!");
        infoUsername.setContentText("Pastikan username kamu belum terdaftar dalam sistem!");

        Alert infoPassword = new Alert(Alert.AlertType.INFORMATION);
        infoPassword.setTitle("Information");
        infoPassword.setHeaderText("Password lemah");
        infoPassword.setContentText("Pastikan password minimal 10 character!");

        Alert infoField = new Alert(Alert.AlertType.INFORMATION);
        infoPassword.setTitle("Information");
        infoPassword.setHeaderText("Tidak boleh ada field yang kosong!");
        infoPassword.setContentText("Mohon lengkapi field!");

        //action
        login.setOnMouseClicked(MouseEvent->{
            toLogin();
        });

        signUp.setOnMouseClicked(ev->{
            actionRegis(infoField,infoPassword,infoUsername);

        });

        noHp.setOnKeyReleased(KeyEvent->{
            if(KeyEvent.getCode().toString().equalsIgnoreCase("enter")){
                actionRegis(infoField,infoPassword,infoUsername);
            }
        });

    }

    private void modalAlert(String h){
        // deklarasi alert
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Information");
        info.setHeaderText(h);
        info.showAndWait();
    }

    private int actionRegis(Alert infoField,Alert infoPassword,Alert infoUsername){
        String dataUsername = username.getText();
        String dataNamaLengkap = namaLengkap.getText();
        String dataPassword = password.getText();
        String dataNoHp = noHp.getText();


        // checking field tidak boleh kosong dan password min 10 char
        if(dataNamaLengkap.isBlank() || dataNoHp.isBlank() || dataUsername.isBlank() || dataPassword.isBlank()){
            infoField.showAndWait();
            reset();
            return 0;

        }else if(dataPassword.length() < 10){
            infoPassword.showAndWait();
            reset();
            return 0;
        }

        // checking apakah username sudah terdaftar atau tidak
        for (User member:allMember) {
            if(dataUsername.equalsIgnoreCase(member.username)){
                infoUsername.showAndWait();
                reset();
                return 0;
            }
        }

        int report = Setter.sendRegistrasi(dataUsername,dataPassword,dataNamaLengkap,dataNoHp);

        if (report == 0) {
            modalAlert("Data gagal di Upload");
        } else if (report == -1) {
            modalAlert("Kesalahan SQL Syntax");
        } else if (report == 1) {
            modalAlert("Data berhasil di Upload");
        }
        toLogin();
        return 1;
    }


    private void toLogin(){
        stage = (Stage) login.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(App.class.getResource("loginView.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root, 400, 480);
        scene.getStylesheets().add(App.class.getResource("style/login.css").toExternalForm());
        stage.setTitle("Login");
        stage.setScene(scene);
    }

    private void reset(){
        username.setText("");
        password.setText("");
        namaLengkap.setText("");
        noHp.setText("");
    }
}