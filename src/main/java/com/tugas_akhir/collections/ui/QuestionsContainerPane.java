package com.tugas_akhir.collections.ui;

import com.tugas_akhir.collections.Comment;
import com.tugas_akhir.collections.Question;
import com.tugas_akhir.config.Getter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class QuestionsContainerPane extends Pane {

    private double jumlahPertanyaan = 0;
    public QuestionsContainerPane(boolean topQuestions){
        ArrayList<Question> questions = null;

        if(topQuestions){
            questions = Getter.getTopQuestion();
        }else {
            questions = Getter.getAllQuestion();
        }


        for (Question question: questions) {
            QuestionPane a = new QuestionPane(this.getChildren().size() - 1);
            ArrayList<Comment> comments = Getter.getComments(question.idPertanyaan);

            a.setAuthor(question.author);
            a.setPertanyaan(question.judul,question.body);
            a.setVotes(question.like);
            a.setKategori(question.kategori[0]);
            a.idPertanyaan = question.idPertanyaan;
            a.setComments(comments.size());

            this.getChildren().add(a);
            jumlahPertanyaan++;
        }
    }

    public QuestionsContainerPane(String kategori){
        ArrayList<Question> questions = Getter.getAllQuestions(kategori);

        for (Question question: questions) {
            QuestionPane a = new QuestionPane(this.getChildren().size() - 1);
            ArrayList<Comment> comments = Getter.getComments(question.idPertanyaan);

            a.setAuthor(question.author);
            a.setPertanyaan(question.judul,question.body);
            a.setVotes(question.like);
            a.setKategori(question.kategori[0]);
            a.idPertanyaan = question.idPertanyaan;
            a.setComments(comments.size());

            this.getChildren().add(a);
            jumlahPertanyaan++;
        }
    }

    public void setHeightParent(double y){
        BorderPane parent = (BorderPane) this.getParent();
        parent.setPrefHeight(((y + 190) * this.jumlahPertanyaan)+170);
    }
}
