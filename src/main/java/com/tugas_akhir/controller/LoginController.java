package com.tugas_akhir.controller;

import com.tugas_akhir.App;
import com.tugas_akhir.config.SistemLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class LoginController {
    @FXML
    private TextField idusername;

    @FXML
    private PasswordField idpassword;

    @FXML
    private Hyperlink forget;

    @FXML
    private Button login;

    @FXML
    private BorderPane background;
    private Stage stage;
    public void initialize(){
        evHandler();
    }

    public void evHandler(){
        // deklarasi alert
        Alert infoUsername = new Alert(Alert.AlertType.INFORMATION);
        infoUsername.setTitle("Information");
        infoUsername.setHeaderText("Username tidak terdaftar!");
        infoUsername.setContentText("Pastikan username kamu telah terdaftar");

        Alert infoPassword = new Alert(Alert.AlertType.INFORMATION);
        infoPassword.setTitle("Information");
        infoPassword.setHeaderText("Password salah!");
        infoPassword.setContentText("Pastikan password kamu telah benar");


        login.setOnMouseClicked(MouseEvent->{
            stage = (Stage) background.getScene().getWindow();
            int result = SistemLogin.login(idusername.getText(), idpassword.getText());
            if( result == 0){
                if(infoUsername.showAndWait().get() == ButtonType.OK){
                    System.out.println("Username tidak terdaftar!");
                }
            }else if(result == -1){
                if(infoPassword.showAndWait().get() == ButtonType.OK){
                    System.out.println("Password salah!");
                }
            }else{
                System.out.println("berhasil login");
                stage.close();
            }
        });

        idpassword.setOnKeyReleased(KeyEvent->{
            stage = (Stage) background.getScene().getWindow();
            if(KeyEvent.getCode().toString().equalsIgnoreCase("enter")){
                int result = SistemLogin.login(idusername.getText(), idpassword.getText());
                if( result == 0){
                    if(infoUsername.showAndWait().get() == ButtonType.OK){
                        System.out.println("Username tidak terdaftar!");
                    }
                }else if(result == -1){
                    if(infoPassword.showAndWait().get() == ButtonType.OK){
                        System.out.println("Password salah!");
                    }
                }else{
                    System.out.println("berhasil login");
                    stage.close();
                }
            }
        });

        forget.setOnMouseClicked(ev->{
            stage = (Stage) background.getScene().getWindow();
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("regisView.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Scene scene = new Scene(root, 400, 550);

            String css = Objects.requireNonNull(App.class.getResource("style/registrasi.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("Registrasi");

            stage.setScene(scene);
        });
    }


}