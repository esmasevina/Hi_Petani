package com.tugas_akhir.collections.ui.detail;

import com.tugas_akhir.App;
import com.tugas_akhir.collections.Question;
import com.tugas_akhir.config.Getter;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class DetailQuestionPaneTop extends Pane {

    private Label judul,author,isi;
    private Pane likepane, commentpane;
    private ImageView likeIcon, commentIcon;
    private Hyperlink like,comment;

    public Hyperlink getLike() {
        return like;
    }

    private Stage loginOrRegis,appStage;
    public DetailQuestionPaneTop(int idPertanyaan){
        Question data = Getter.getQuestion(idPertanyaan);

        judul = new Label(data.judul);
        author = new Label("By "+data.author);
        isi = new Label(data.body);

        // custom judul
        judul.getStyleClass().add("judulPertanyaanDetail");
        judul.setLayoutX(120.0);
        judul.setLayoutY(37.0);


        // custom author
        author.getStyleClass().add("authorPertanyaanDetail");
        author.setLayoutX(123.0);
        author.setLayoutY(100.0);

        //custom isi
        isi.getStyleClass().add("isiPertanyaanDetail");
        isi.setWrapText(true);
        isi.setLayoutX(125.0);
        isi.setLayoutY(140.0);
        isi.setPrefSize(800,450);

        // custom likeIcon
        likeIcon = new ImageView(new Image(App.class.getResource("icons/likeIcon.png").toExternalForm()));
        likeIcon.setFitWidth(20);
        likeIcon.setFitHeight(20);
        likeIcon.setLayoutX(20.0);
        likeIcon.setLayoutY(9.5);
        likeIcon.getStyleClass().add("animateUp");

        // custom like
        like = new Hyperlink("Like");
        like.getStyleClass().add("detailLikeComment");

        // custom like group
        likepane = new Pane();
        likepane.getChildren().add(like);
        likepane.getChildren().add(likeIcon);
        likepane.getStyleClass().add("animateUpp");
        likepane.setPrefSize(100,30);
        likepane.setLayoutX(130.0);
        likepane.setLayoutY(454.0);

        // custom commentIcon
        commentIcon = new ImageView(new Image(App.class.getResource("icons/commentIcon.png").toExternalForm()));
        commentIcon.setFitWidth(20);
        commentIcon.setFitHeight(20);
        commentIcon.setLayoutX(20.0);
        commentIcon.setLayoutY(9.5);
        commentIcon.getStyleClass().add("animateUp");

        // custom comment
        comment = new Hyperlink("Comment | 0");
        comment.getStyleClass().add("detailLikeComment");

        // custom comment group
        commentpane = new Pane();
        commentpane.getChildren().add(comment);
        commentpane.getChildren().add(commentIcon);
        commentpane.getStyleClass().add("animateUpp");
        commentpane.setPrefSize(100,30);
        commentpane.setLayoutX(250);
        commentpane.setLayoutY(453.5);


        this.setPrefSize(1100,500);
        this.getChildren().add(judul);
        this.getChildren().add(author);
        this.getChildren().add(isi);
        this.getChildren().add(likepane);
        this.getChildren().add(commentpane);
    }


    public void setCommentText(int jumlahComment){
        comment.setText("Comment | "+jumlahComment);
    }
}
