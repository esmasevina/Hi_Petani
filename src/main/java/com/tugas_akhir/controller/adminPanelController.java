package com.tugas_akhir.controller;

import com.tugas_akhir.AdminApp;
import com.tugas_akhir.collections.ui.CustomKategoriPane;
import com.tugas_akhir.config.Getter;
import com.tugas_akhir.config.Setter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class adminPanelController {

    @FXML
    private Pane tempatKategori;

    @FXML
    private Button addBtn;

    private Stage thisStage;

    public void initialize(){
        CustomKategoriPane categories = new CustomKategoriPane();
        tempatKategori.getChildren().add(categories);
        categories.setHeightParent(20);

        for (int index = 0; index<categories.getChildren().size();index++) {
            Node edit = (Node) ((Pane)categories.getChildren().get(index)).getChildren().get(1);
            Node remove = (Node) ((Pane)categories.getChildren().get(index)).getChildren().get(2);

            edit.setOnMouseClicked(mouseEvent -> {
                String[][] rawKategori = Getter.getAllKategori();
                Pane kategori = (Pane)((ImageView)mouseEvent.getSource()).getParent();
                String kategoriText = ((Label)kategori.getChildren().get(0)).getText();
                for (String [] kategor:rawKategori) {
                    int id = 0;
                    // cari id pertanyaan berdasarkan source tombol yang diklik
                    if(kategor[1].equalsIgnoreCase(kategoriText)){
                        editAction(kategor);
                        break;
                    }
                }
            });

            remove.setOnMouseClicked(mouseEvent -> {
                String[][] rawKategori = Getter.getAllKategori();
                Pane kategori = (Pane)((ImageView)mouseEvent.getSource()).getParent();
                String kategoriText = ((Label)kategori.getChildren().get(0)).getText();
                for (String [] kategor:rawKategori) {
                    int id = 0;
                    // cari id pertanyaan berdasarkan source tombol yang diklik
                    if(kategor[1].equalsIgnoreCase(kategoriText)){
                        removeAction(kategor);
                        break;
                    }
                }
            });

        }


        addBtn.setOnMouseClicked(ev->{
            categories.warningAlert("Tolong kosongkan form jika tidak ingin menambah kategori!");
            TextInputDialog valueKat = new TextInputDialog("Masukan kategori !");
            valueKat.setHeaderText("Kosong kan form jika ingin membatalkan penambahan!");
            valueKat.setTitle("Menambah kategori");

            valueKat.showAndWait();
            Alert information;

            if(valueKat.getEditor().getText().equalsIgnoreCase("")){
                information = new Alert(Alert.AlertType.INFORMATION);
                information.setHeaderText("Data tidak boleh kosong Kakak!");
                information.showAndWait();
                valueKat.close();
            }else{
                int infoUpdate = Setter.sendKategori(valueKat.getEditor().getText(),1);

                if (infoUpdate == 0) {
                    categories.modalAlert("Kategori gagal di Tambah");
                } else if (infoUpdate == -1) {
                    categories.modalAlert("Kesalahan SQL Syntax");
                } else if (infoUpdate == 1) {
                    categories.modalAlert("Kategori berhasil di Tambah");
                }  else if (infoUpdate == -55) {
                    categories.modalAlert("Kategori sudah ada");
                }
                valueKat.close();
            }
            refresh();
        });
    }

    private void refresh(){
        thisStage = (Stage) tempatKategori.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(AdminApp.class.getResource("adminPanel.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 350, 510);

        scene.getStylesheets().add(AdminApp.class.getResource("style/adminPanel.css").toExternalForm());

        thisStage.setScene(scene);
    }

    // methode untuk actionRemove
    private void removeAction(String [] k){
        int id = Integer.valueOf(k[0]);
        int infoUpdate = Setter.removeKategori(id);

        if (infoUpdate == 0) {
            modalAlert("Kategori gagal di Hapus");
        } else if (infoUpdate == -1) {
            modalAlert("Kesalahan SQL Syntax");
        } else if (infoUpdate == 1) {
            modalAlert("Kategori berhasil di Hapus");
        }
        refresh();

    }

    // methode untuk actionEdit
    private void editAction(String [] k){
        int id = Integer.valueOf(k[0]);
        warningAlert("Tolong kosongkan form jika tidak ingin mengubahnya!");
        TextInputDialog valueKat = new TextInputDialog(k[1]);
        valueKat.setHeaderText("Kosong kan form jika ingin membatalkan perubahan!");
        valueKat.setTitle("Mengubah kategori");

        valueKat.showAndWait();
        Alert information;

        if(valueKat.getEditor().getText().equalsIgnoreCase("")){
            information = new Alert(Alert.AlertType.INFORMATION);
            information.setHeaderText("Data tidak boleh kosong Kakak!");
            information.showAndWait();
            valueKat.close();
        }else{
            int infoUpdate = Setter.changeKategori(id,valueKat.getEditor().getText());

            if (infoUpdate == 0) {
                modalAlert("Kategori gagal di Ubah");
            } else if (infoUpdate == -1) {
                modalAlert("Kesalahan SQL Syntax");
            } else if (infoUpdate == 1) {
                modalAlert("Kategori berhasil di Ubah");
            }
            valueKat.close();
        }

        resfresh();

    }

    // method untuk resfresj kategori
    private void resfresh(){
        thisStage = (Stage) tempatKategori.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(AdminApp.class.getResource("adminPanel.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 350, 510);

        scene.getStylesheets().add(AdminApp.class.getResource("style/adminPanel.css").toExternalForm());

        thisStage.setScene(scene);
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

}
