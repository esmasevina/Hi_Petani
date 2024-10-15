package com.tugas_akhir.collections.ui;

import com.tugas_akhir.App;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class QuestionPane extends Pane {
    private Label isiPertanyaan,votes,comments,author;

    public int idPertanyaan;
    private Hyperlink judulPertanyaan;
    private ImageView voteIcon, commentIcon, foto;
    private Button kategori;

    public void setVotes(int jumlah) {
        votes.setText(jumlah +" Votes");
    }

    public void setComments(int jumlah) {
        comments.setText(jumlah +" Comments");
    }



    public void setPertanyaan(String judulPertanyaan, String isiPertanyaan) {
        this.judulPertanyaan.setText(judulPertanyaan);
        if(isiPertanyaan.length()>149) {
            this.isiPertanyaan.setText(isiPertanyaan.substring(0, 79)+"\n"+isiPertanyaan.substring(79,153)+"\n"+ isiPertanyaan.substring(153,229)+"...");
        }else if(isiPertanyaan.length() > 80){
            this.isiPertanyaan.setText(isiPertanyaan.substring(0, 79)+"...");
        }else{
            this.isiPertanyaan.setText(isiPertanyaan);
        }
    }
    public void setAuthor(String name) {
        author.setText(name);
    }

    public void setKategori(String name) {
        kategori.setText(name);
    }




    public QuestionPane(double jumlahQuestion){

        /* initialize foto*/
        foto = new ImageView(new Image(Objects.requireNonNull(App.class.getResource("icons/Group.png")).toExternalForm()));
        foto.setLayoutX(639.0);
        foto.setLayoutY(142.0);
        foto.setFitWidth(20.0);
        foto.setFitHeight(20.0);
        foto.setPreserveRatio(true);
        foto.setPickOnBounds(true);

        /* initialize commentIcon*/
        commentIcon = new ImageView(new Image(Objects.requireNonNull(App.class.getResource("icons/comment.png")).toExternalForm()));
        commentIcon.setLayoutX(39.0);
        commentIcon.setLayoutY(73.0);
        commentIcon.setFitWidth(25.0);
        commentIcon.setFitHeight(25.0);
        commentIcon.setPreserveRatio(true);
        commentIcon.setSmooth(false);
        commentIcon.setBlendMode(BlendMode.MULTIPLY);
        commentIcon.setPickOnBounds(true);

        /* initialize voteIcon*/
        voteIcon = new ImageView(new Image(Objects.requireNonNull(App.class.getResource("icons/vote.png")).toExternalForm()));
        voteIcon.setLayoutX(39.0);
        voteIcon.setLayoutY(33.0);
        voteIcon.setFitWidth(25.0);
        voteIcon.setFitHeight(25.0);
        voteIcon.setPreserveRatio(true);
        voteIcon.setSmooth(false);
        voteIcon.setBlendMode(BlendMode.MULTIPLY);
        voteIcon.setPickOnBounds(true);

        /* initialize comments*/
        comments = new Label("30 Comments");
        comments.setLayoutX(70.0);
        comments.setLayoutY(74.0);


        /* initialize votes*/
        votes = new Label("20 Votes");
        votes.setLayoutX(70.0);
        votes.setLayoutY(37.0);

        /* initialize author*/
        author = new Label("Stepen");
        author.setLayoutX(664.0);
        author.setLayoutY(144.0);

        /* initialize judulPertanyaan */
        judulPertanyaan = new Hyperlink("Ini judul Pertanyaan");
        judulPertanyaan.getStyleClass().add("judulPertanyaan");
        judulPertanyaan.setLayoutX(155.0);
        judulPertanyaan.setLayoutY(17.0);

        /* initialize isiPertanyaan */
        isiPertanyaan = new Label();
        isiPertanyaan.setText("Ini isi pertanyaan");
        isiPertanyaan.getStyleClass().add("fs-17");
        isiPertanyaan.setLayoutX(165.0);
        isiPertanyaan.setLayoutY(55.0);
        isiPertanyaan.setWrapText(true);

        /* initialize kategoti */
        kategori = new Button("Strowberry");
        kategori.getStyleClass().add("kategori");
        kategori.setLayoutX(156.0);
        kategori.setLayoutY(140.0);
        kategori.setMnemonicParsing(false);



        this.getChildren().addAll(  isiPertanyaan,
                                    judulPertanyaan,
                                    author,
                                    votes,
                                    comments,
                                     voteIcon,
                                    commentIcon,
                                    foto,
                                    kategori
                                 );

        this.setPrefSize(800,180);
        this.getStyleClass().add("root");
        this.getStylesheets().add(Objects.requireNonNull(App.class.getResource("style/question.css")).toExternalForm());
        this.setLayoutX(125.0);
        if (jumlahQuestion == 0) {
            this.setLayoutY(200);
        }else{
            this.setLayoutY((190 * jumlahQuestion) + 200);
        }


    }
}