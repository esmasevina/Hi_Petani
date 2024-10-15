package com.tugas_akhir.collections.ui;

import com.tugas_akhir.App;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CenterAboutContactPane extends Pane{
    private Label isiAbout1,isiAbout2,judulContact,noTelp,email;
    private ImageView telpIcon,emailIcon;
    private Pane contactPane;

    public CenterAboutContactPane(){
        contactPane = new Pane();
        judulContact = new Label("Contact Us");
        noTelp = new Label("0813-8642-8348");
        email = new Label("hipetani@example.com");
        telpIcon = new ImageView(new Image(App.class.getResource("icons/telephone.png").toExternalForm()));
        emailIcon = new ImageView(new Image(App.class.getResource("icons/email.png").toExternalForm()));
        isiAbout1 = new Label("  Sebuah aplikasi bernama Hi-Petani akan dirancang untuk membantu petani berkonsultasi terkait tanaman pertaniannya kepada " +
                                    "para petani lainnya yang menjadi member Hi-Petani. Aplikasi ini memungkinkan setiap member untuk memposting pertanyaan seputar " +
                                    "permasalahan terkait tanaman pertaniannya dan juga dapat menjawab pertanyaan dari member lain. Pertanyaan- pertanyaan para member dikategorikan berdasarkan jenis tanaman terkait " +
                                    "(dipilih saat member akan memposting pertanyaan).");
        isiAbout2 = new Label("  Hal tersebut supaya member-member lainnya dapat dengan mudah menemukan " +
                                    "pertanyaan-pertanyaan yang dapat mereka jawab sesuai dengan keahliannya. Setiap kali member mengunggah pertanyaan, " +
                                    "member lain dapat memberikan like pada pertanyaan tersebut. Pada menu TOP 5 Questions, aplikasi akan menampilkan “TOP 5” pertanyaan yang paling banyak memperoleh like.");

        // setting untuk contactPane
        judulContact.getStyleClass().add("judulAboutContact");
        judulContact.setLayoutX(125);
        judulContact.setLayoutY(367);
        contactPane.getChildren().add(judulContact);

        telpIcon.setFitHeight(35);
        telpIcon.setFitWidth(35);
        telpIcon.setLayoutX(135);
        telpIcon.setLayoutY(435);
        contactPane.getChildren().add(telpIcon);

        emailIcon.setFitHeight(30);
        emailIcon.setFitWidth(38);
        emailIcon.setLayoutX(135);
        emailIcon.setLayoutY(490);
        contactPane.getChildren().add(emailIcon);

        noTelp.setLayoutX(190);
        noTelp.setLayoutY(435);
        noTelp.getStyleClass().add("noTelpEmail");
        contactPane.getChildren().add(noTelp);

        email.setLayoutX(190);
        email.setLayoutY(490);
        email.getStyleClass().add("noTelpEmail");
        contactPane.getChildren().add(email);

        isiAbout1.setWrapText(true);
        isiAbout1.setPrefWidth(800);
        isiAbout1.setLayoutX(125);
        isiAbout1.setLineSpacing(4);
        isiAbout1.setLayoutY(0);
        isiAbout1.getStyleClass().add("fs-18");

        isiAbout2.setWrapText(true);
        isiAbout2.setPrefWidth(800);
        isiAbout2.setLineSpacing(4);
        isiAbout2.setLayoutX(125);
        isiAbout2.setLayoutY(145);
        isiAbout2.getStyleClass().add("fs-18");


        contactPane.setLayoutY(50);


        // setting this pane
        this.setPrefSize(1100,800);
        this.getChildren().add(isiAbout1);
        this.getChildren().add(isiAbout2);
        this.getChildren().add(contactPane);
    }

    public void setHeightParent(double y){
        BorderPane parent = (BorderPane) this.getParent();
        parent.setPrefHeight(y);
    }

}
