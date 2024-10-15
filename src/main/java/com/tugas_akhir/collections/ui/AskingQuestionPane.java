package com.tugas_akhir.collections.ui;

import com.tugas_akhir.collections.ui.detail.TextAreaPane;
import com.tugas_akhir.config.Getter;
import com.tugas_akhir.config.Setter;
import com.tugas_akhir.config.SistemLogin;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.ArrayList;


public class AskingQuestionPane extends Pane {
    private Pane panel,panelTextArea;
    private Label askQuestion,title,body,kategoriLabel;
    private TextAreaPane textArea;

    private String[][] rawOpsiKategori = Getter.getAllKategori();

    private TextField titleArea;
    private Button submit;
    private ChoiceBox<String> kategori;

    public AskingQuestionPane(){
        // deklarasi objek yang dibutuhkan
        panel = new Pane();
        panelTextArea = new Pane();
        askQuestion = new Label("Ask a question");
        title = new Label("Title");
        body = new Label("Body");
        kategoriLabel =  new Label("Categories");
        textArea = new TextAreaPane();
        titleArea = new TextField();
        kategori = new ChoiceBox<>();
        submit = new Button("Submit your question");

        // mengolah opsi kategori
        for (String [] opsiKategori: rawOpsiKategori) {

            kategori.getItems().add(" "+capitalize(opsiKategori[1])+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        }


        // grouping & custom textArea
        panelTextArea.getChildren().add(textArea);
        textArea.getTextArea().setPrefHeight(250);
        panelTextArea.getStyleClass().add("border");
        panelTextArea.setLayoutY(300);

        // custom askQuestion
        askQuestion.getStyleClass().add("judulAskingQuestion");
        askQuestion.setLayoutX(0);
        askQuestion.setLayoutY(40);

        // custom title
        title.getStyleClass().add("labelAskingQuestion");
        title.setLayoutX(0);
        title.setLayoutY(140);

        // custom titleArea
        titleArea.getStyleClass().add("border");
        titleArea.setLayoutX(0);
        titleArea.setLayoutY(180);
        titleArea.setPrefSize(943,30);
        titleArea.setFont(new Font("arial",24));

        // custom body
        body.getStyleClass().add("labelAskingQuestion");
        body.setLayoutX(0);
        body.setLayoutY(260);

        // custom kategori label
        kategoriLabel.getStyleClass().add("labelAskingQuestion");
        kategoriLabel.setLayoutX(0);
        kategoriLabel.setLayoutY(615);

        // custom submit
        submit.getStyleClass().add("submitBtn");
        submit.setLayoutX(0);
        submit.setLayoutY(750);


        // custom kategori
        kategori.getStyleClass().add("choiceBoxKategori");
        kategori.getStyleClass().add("border");
        kategori.setLayoutX(0);
        kategori.setLayoutY(655);
        kategori.setPrefSize(943,40);
        kategori.prefWidth(943);
        kategori.setValue("Pilih...");




        // grouping komponen
        panel.getChildren().add(askQuestion);
        panel.getChildren().add(title);
        panel.getChildren().add(titleArea);
        panel.getChildren().add(body);
        panel.getChildren().add(panelTextArea);
        panel.getChildren().add(kategoriLabel);
        panel.getChildren().add(kategori);
        panel.getChildren().add(submit);

        panel.setPrefSize(950,800);
        panel.setLayoutX(100);

        this.getChildren().add(panel);
        sendData();
    }

    private void sendData(){
        submit.setOnMouseClicked(MouseEvent->{
            String judul = titleArea.getText();
            String isi = textArea.getText();
            int kodeKategori = getKodeValue(kategori.getValue());

            if(judul.isBlank()) {
                modalAlert("Title tidak boleh kosong !");
            }else if(isi.isBlank()){
                modalAlert("Body tidak boleh kosong !");
            } else if (kodeKategori == 0) {
                modalAlert("Pilihlah setidaknya 1 kategori!");
            }else{
                int infoUpdate = Setter.sendQuestion(judul, isi, SistemLogin.userId, kodeKategori);
                if (infoUpdate == 0) {
                    modalAlert("Pertanyaan gagal di Upload");
                } else if (infoUpdate == -1) {
                    modalAlert("Kesalahan SQL Syntax");
                } else if (infoUpdate == 1) {
                    modalAlert("Pertanyaan berhasil di Upload");
                }

                resetKomponent();
            }
        });
    }

    private String capitalize(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private int getKodeValue(String value){
        for (String[] opsiKategori: rawOpsiKategori) {
            String pembanding = " "+opsiKategori[1]+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
            if(value.equalsIgnoreCase(pembanding)){
                return Integer.valueOf(opsiKategori[0]);
            }
        }

        return 0;
    }

    private void modalAlert(String h){
        // deklarasi alert
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Information");
        info.setHeaderText(h);
        info.show();
    }

    private void resetKomponent(){
        // reset komponent
        textArea.getTextArea().setText("");
        titleArea.setText("");
        kategori.setValue("Pilih...");
    }


}
