package com.tugas_akhir.collections.ui.detail;

import com.tugas_akhir.App;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



public class CommentQuestion extends Pane {
    private ImageView komentatorImage;
    private Label isiKomen,komentator;

    public CommentQuestion(){
        komentatorImage = new ImageView(new Image(App.class.getResource("icons/Group.png").toExternalForm()));
        isiKomen = new Label("(default) Ini adalah Komentar");
        komentator = new Label("(default) ini adalah komentator");

        // custom komentatorImage
        komentatorImage.setFitWidth(33);
        komentatorImage.setFitHeight(33);
        komentatorImage.setLayoutX(10);
        komentatorImage.setLayoutY(10);

        // custom komentator
        komentator.setLayoutX(47);
        komentator.setLayoutY(13);
        komentator.getStyleClass().add("komentator");

        // custom isi komentar
        isiKomen.setPrefSize(850,250);
        isiKomen.setWrapText(true);
        isiKomen.setLayoutX(47);
        isiKomen.setLayoutY(50);
        isiKomen.getStyleClass().add("font-20");



        this.getChildren().add(komentatorImage);
        this.getChildren().add(komentator);
        this.getChildren().add(isiKomen);

        this.setPrefSize(900,300);
    }

    public void setKomentator(String namaKomentator){
        this.komentator.setText(namaKomentator);
    }

    public void setIsiKomen(String isiKomen){
        this.isiKomen.setText(isiKomen);
    }

}
