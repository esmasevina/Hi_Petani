package com.tugas_akhir.collections.ui.detail;

import com.tugas_akhir.collections.Comment;
import com.tugas_akhir.config.Getter;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class DetailQuestionPaneCenter extends Pane {

    private Label totalComment;
    private Pane border;

    public int totalKomentar;
    public DetailQuestionPaneCenter(int idPertanyaan){
        border = new Pane();
        // mengambil data
        ArrayList<Comment> rawData = Getter.getComments(idPertanyaan);

        try{
            // menampilkan visual komentar group
            for (Comment data: rawData) {
                CommentQuestion komentar = new CommentQuestion();
                komentar.setKomentator(data.namaKomentator);
                komentar.setIsiKomen(data.isiKomentar);
                border.getChildren().add(komentar);

                //custom komentar
                komentar.setLayoutX(30);
                komentar.getStyleClass().add("bd-bot");
                if(border.getChildren().size() == 1)
                    komentar.setLayoutY(50);
                else {
                    komentar.setLayoutY(50 + ((300 * (border.getChildren().size() - 1))));
                }


            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

//        for (int a = 1; a<=3 ; a++){
//            CommentQuestion komentar = new CommentQuestion();
//            border.getChildren().add(komentar);
//
//            //custom komentar
//            komentar.setLayoutX(30);
//            komentar.getStyleClass().add("bd-bot");
//            if(border.getChildren().size() == 1)
//                komentar.setLayoutY(50);
//            else
//                komentar.setLayoutY(50 +  ((300 * (border.getChildren().size()-1))));
//        }

        // menghapus border bawah dari pertanyaan yang paling akhir
        if(border.getChildren().size() != 0)
            border.getChildren().get(border.getChildren().size() - 1).getStyleClass().remove(0);


        totalKomentar = border.getChildren().size();
        totalComment = new Label("Comments ("+(border.getChildren().size())+")" );


        // custom total Comment
        totalComment.getStyleClass().add("totalKomentar");
        totalComment.setLayoutX(120.0);
        totalComment.setLayoutY(22);

        // custom border
        border.setLayoutX(100.0);
        border.setLayoutY(20);
        border.getStyleClass().add("border");
        if(border.getChildren().size() < 2){
            border.setPrefSize(950,600);
        }else{
            border.setPrefSize(950,(310*(border.getChildren().size()-1))+360);
        }

        this.getChildren().add(border);
        this.getChildren().add(totalComment);
    }

    public void setHeightParent(){
        ((BorderPane)this.getParent()).setPrefHeight(border.getPrefHeight() + 600);
    }
}
