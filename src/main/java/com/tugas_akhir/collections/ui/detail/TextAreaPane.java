package com.tugas_akhir.collections.ui.detail;

import com.tugas_akhir.App;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class TextAreaPane extends Pane {
    private TextArea textArea;
    private Pane panelControl,fotoButton,videoButton,linkButton,panelControlLeft,panelControlRight;
    private ImageView boldButton,italicButton,dotListButton,numberListButton,fotoIcon,videoIcon,linkIcon;
    private Label foto,video,link;

    public TextAreaPane(){
        // deklarasi
        textArea = new TextArea();
        panelControl = new Pane();
        panelControlLeft = new Pane();
        panelControlRight = new Pane();
        fotoButton = new Pane();
        videoButton = new Pane();
        linkButton = new Pane();
        boldButton = new ImageView(new Image(App.class.getResource("icons/boldIcon.png").toExternalForm()));
        italicButton = new ImageView(new Image(App.class.getResource("icons/italicIcon.png").toExternalForm()));
        dotListButton = new ImageView(new Image(App.class.getResource("icons/dotListIcon.png").toExternalForm()));
        numberListButton = new ImageView(new Image(App.class.getResource("icons/numberListIcon.png").toExternalForm()));
        fotoIcon = new ImageView(new Image(App.class.getResource("icons/fotoIcon.png").toExternalForm()));
        videoIcon = new ImageView(new Image(App.class.getResource("icons/videoIcon.png").toExternalForm()));
        linkIcon = new ImageView(new Image(App.class.getResource("icons/linkIcon.png").toExternalForm()));
        foto = new Label("Photo");
        video = new Label("Video");
        link = new Label("Link");

        // grouping
            //foto button
            fotoButton.getChildren().add(fotoIcon);
            fotoButton.getChildren().add(foto);

            //video button
            videoButton.getChildren().add(videoIcon);
            videoButton.getChildren().add(video);

            //link button
            linkButton.getChildren().add(linkIcon);
            linkButton.getChildren().add(link);

            // panelLeft
            panelControlLeft.getChildren().add(boldButton);
            panelControlLeft.getChildren().add(italicButton);
            panelControlLeft.getChildren().add(dotListButton);
            panelControlLeft.getChildren().add(numberListButton);

            // panelRight
            panelControlRight.getChildren().add(fotoButton);
            panelControlRight.getChildren().add(videoButton);
            panelControlRight.getChildren().add(linkButton);

            // panelControl
            panelControl.getChildren().add(panelControlLeft);
            panelControl.getChildren().add(panelControlRight);

        // custom fotoIcon
        fotoIcon.setFitWidth(25);
        fotoIcon.setFitHeight(20);

        // custom videoIcon
        videoIcon.setFitWidth(35);
        videoIcon.setFitHeight(20);

        // custom linkIcon
        linkIcon.setFitWidth(25);
        linkIcon.setFitHeight(20);

        // custom boldButton
        boldButton.setFitWidth(15);
        boldButton.setFitHeight(15);
        boldButton.setLayoutX(20);
        boldButton.setLayoutY(8);

        // custom italicButton
        italicButton.setFitWidth(15);
        italicButton.setFitHeight(15);
        italicButton.setLayoutX(70);
        italicButton.setLayoutY(8);

        // customDotList
        dotListButton.setFitHeight(15);
        dotListButton.setFitWidth(15);
        dotListButton.setLayoutX(120);
        dotListButton.setLayoutY(8);

        // custom numberList
        numberListButton.setFitWidth(15);
        numberListButton.setFitHeight(15);
        numberListButton.setLayoutX(170);
        numberListButton.setLayoutY(8);


        // custom foto
        foto.getStyleClass().add("fontControlPanel");
        foto.setLayoutX(30);

        // custom video
        video.getStyleClass().add("fontControlPanel");
        video.setLayoutX(40);

        // custom link
        link.getStyleClass().add("fontControlPanel");
        link.setLayoutX(30);

        // custom fotoButton
        fotoButton.setLayoutX(30);
        fotoButton.setLayoutY(5);

        // custom videoButton
        videoButton.setLayoutX(130);
        videoButton.setLayoutY(5);


        // custom fotoButton
        linkButton.setLayoutX(240);
        linkButton.setLayoutY(5);

        // custom textArea
        textArea.setScrollLeft(0);
        textArea.setLayoutX(1);
        textArea.setLayoutY(31);
        textArea.setPrefSize(940,300);
        textArea.setWrapText(true);
        textArea.setFont(new Font("arial",24));
        textArea.setOnMouseClicked(MouseEvent-> textArea.getSkin().getNode().setStyle("-fx-background-color: white;"));

        // custom panelControlLeft
        panelControlLeft.getStyleClass().add("border-noRadiusLeft");
        panelControlLeft.setPrefSize(210,30);
        panelControlLeft.setLayoutX(0);
        panelControlLeft.setLayoutY(0);

        // custom panelControlLeft
        panelControlRight.setLayoutX(210);
        panelControlRight.setLayoutY(0);
        panelControlRight.setPrefSize(700,30);

        //custom panelControl
        panelControl.setLayoutX(0);
        panelControl.setLayoutY(0);
        panelControl.getStyleClass().add("border-noRadius");
        panelControl.setPrefSize(940.5,30);

        this.getChildren().add(panelControl);
        this.getChildren().add(textArea);
    }

    public String getText(){
        return textArea.getText();
    }
    public void setText(String s){
        textArea.setText(s);
    }

    public TextArea getTextArea(){
        return textArea;
    }
}
