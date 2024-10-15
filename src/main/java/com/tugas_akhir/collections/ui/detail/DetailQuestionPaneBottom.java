package com.tugas_akhir.collections.ui.detail;

import com.tugas_akhir.App;
import com.tugas_akhir.config.SistemLogin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DetailQuestionPaneBottom extends Pane {
    private Pane panel;

    public Button post;
    private Label judulYourComment;
    public TextAreaPane textArea;

    private Stage loginOrRegis;
    public DetailQuestionPaneBottom(){
        post = new Button("Post Your Comment");
        judulYourComment = new Label("Your Comment");
        panel = new Pane();
        textArea = new TextAreaPane();
        panel.getChildren().add(textArea);
        panel.getChildren().add(judulYourComment);
        panel.getChildren().add(post);

        // custom
        judulYourComment.getStyleClass().add("totalKomentar");
        post.getStyleClass().add("postButton");
        textArea.getStyleClass().add("border");
        textArea.setLayoutX(0);
        textArea.setLayoutY(50);
        textArea.setLayoutX(0);
        post.setLayoutY(410);
        panel.setLayoutX(100);
        panel.setLayoutY(0);



        this.setPrefSize(1100,500);
        this.getChildren().add(panel);
    }

    public void setHeightParent(){
        ((BorderPane)this.getParent()).setPrefHeight(((BorderPane) this.getParent()).getPrefHeight()+this.getPrefHeight());
    }
}
