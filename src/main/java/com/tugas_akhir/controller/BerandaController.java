package com.tugas_akhir.controller;

import com.tugas_akhir.App;
import com.tugas_akhir.collections.ui.*;
import com.tugas_akhir.collections.ui.detail.DetailQuestionPaneBottom;
import com.tugas_akhir.collections.ui.detail.DetailQuestionPaneCenter;
import com.tugas_akhir.collections.ui.detail.DetailQuestionPaneTop;
import com.tugas_akhir.config.Setter;
import com.tugas_akhir.config.SistemLogin;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventDispatcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class BerandaController {

    @FXML
    private ScrollPane mainContainer;

    private String valueComment = "";

    @FXML
    private Button loginBtn;

    @FXML
    private Label totalQuestions;

    @FXML
    public ImageView poto;

    @FXML
    private Label judulTopQuestionContainer;

    @FXML
    private Pane sidebar,
                 topQuestionsContainer,
                header;

    private QuestionsContainerPane panelPertanyaan;

    @FXML
    private Hyperlink   user,
                        home,
                        about,
                        contact,
                        topQuestions;

    private int totalComment = 0;

    private Stage loginOrRegis;

    @FXML
    private BorderPane questionsContainer;

    private KategoriPane panelKategori = new KategoriPane();

    // mehtode ketika ada yang mentrigger perubahan di kategori
    public void kategoriAction(MouseEvent ev){
        // menelusuri source checkbox
        KategoriPane panel = (KategoriPane) sidebar.getChildren().get(sidebar.getChildren().size()-1);
        linkClicked(ev);
        if(ev.getEventType().getName().equalsIgnoreCase("MOUSE_CLICKED")){
            for (Node node:  panel.getChildren()) {
                CheckBox ch = (CheckBox) node;
                if(ch.getText().equalsIgnoreCase(((Button)ev.getSource()).getText())){
                    ch.setSelected(true);
                }else{
                    ch.setSelected(false);
                }

                // action untuk checkbox yang aktif
                if(ch.isSelected()){
                    if( !ch.getText().equalsIgnoreCase("All Questions")) {
                        // mengubah judulnya
                        questionsContainer.setTop(topQuestionsContainer);
                        judulTopQuestionContainer.setText("Questions categories [" + ch.getText() + "]");
                        totalQuestions.setText(panelPertanyaan.getChildren().size() + " questions");

                        // mengubah body
                        // menampilkan pertanyaan
                        panelPertanyaan = new QuestionsContainerPane(ch.getText());
                        questionsContainer.setCenter(panelPertanyaan);
                        totalQuestions.setText(panelPertanyaan.getChildren().size() + " questions");
                        // mengubah tinggi borderpane tegantung dari banyaknya jumlah pertanyaan
                        panelPertanyaan.setHeightParent(panelPertanyaan.getChildren().size());
                    }else {
                        // mengubah judulnya
                        questionsContainer.setTop(topQuestionsContainer);
                        judulTopQuestionContainer.setText("All Questions");
                        totalQuestions.setText(panelPertanyaan.getChildren().size() + " questions");

                        // mengubah body
                        // menampilkan pertanyaan
                        panelPertanyaan = new QuestionsContainerPane(false);
                        questionsContainer.setCenter(panelPertanyaan);
                        totalQuestions.setText(panelPertanyaan.getChildren().size() + " questions");
                        // mengubah tinggi borderpane tegantung dari banyaknya jumlah pertanyaan
                        panelPertanyaan.setHeightParent(panelPertanyaan.getChildren().size());
                    }
                }
            }


        }else{
            for (Node node:  panel.getChildren()) {
                CheckBox ch = (CheckBox) node;

                // menambahkan event jika ada salah satu checkbox yang di klik
                ch.setOnMouseClicked((MouseEvent)->{

                    // untuk memilih mana yang aktif berdasarkan source yang di klik
                    CheckBox active =  (CheckBox) MouseEvent.getSource();
                    for (Node nodef: panel.getChildren()) {
                        CheckBox nodeCt = (CheckBox) nodef;
                        if (nodeCt.getText().equalsIgnoreCase(active.getText())) {
                            nodeCt.setSelected(true);
                        } else {
                            nodeCt.setSelected(false);
                        }
                    }

                    // action untuk checkbox yang aktif
                    if(ch.isSelected()){
                        if( !ch.getText().equalsIgnoreCase("All Questions")) {
                            // mengubah judulnya
                            questionsContainer.setTop(topQuestionsContainer);
                            judulTopQuestionContainer.setText("Questions categories [" + ch.getText() + "]");
                            totalQuestions.setText(panelPertanyaan.getChildren().size() + " questions");

                            // mengubah body
                            // menampilkan pertanyaan
                            panelPertanyaan = new QuestionsContainerPane(ch.getText());
                            questionsContainer.setCenter(panelPertanyaan);
                            totalQuestions.setText(panelPertanyaan.getChildren().size() + " questions");
                            // mengubah tinggi borderpane tegantung dari banyaknya jumlah pertanyaan
                            panelPertanyaan.setHeightParent(panelPertanyaan.getChildren().size());
                        }else {
                            // mengubah judulnya
                            questionsContainer.setTop(topQuestionsContainer);
                            judulTopQuestionContainer.setText("All Questions");
                            totalQuestions.setText(panelPertanyaan.getChildren().size() + " questions");

                            // mengubah body
                            // menampilkan pertanyaan
                            panelPertanyaan = new QuestionsContainerPane(false);
                            questionsContainer.setCenter(panelPertanyaan);
                            totalQuestions.setText(panelPertanyaan.getChildren().size() + " questions");
                            // mengubah tinggi borderpane tegantung dari banyaknya jumlah pertanyaan
                            panelPertanyaan.setHeightParent(panelPertanyaan.getChildren().size());
                        }
                        // mengubah button menjadi null
                        questionsContainer.setBottom(null);
                    }

                    // setting set active pada hyperlink header
                    home.setVisited(true);
                    about.setVisited(false);
                    contact.setVisited(false);
                    user.setVisited(false);
                    topQuestions.setVisited(false);
                });
            }
        }

    }

    // method ketika hyperlink di jalankan
    public void linkClicked(MouseEvent e){
        if(e.getSource().getClass().getName().equalsIgnoreCase("javafx.scene.control.Hyperlink")) {
            Hyperlink source = (Hyperlink) e.getSource();

            // menghapus selected state pada checkbox
            KategoriPane panel = (KategoriPane) sidebar.getChildren().get(sidebar.getChildren().size()-1);
            for (Node node:  panel.getChildren()) {
                CheckBox ch = (CheckBox) node;
                ch.setSelected(false);
            }

            //  membuat pane untuk top main container
            Pane aboutContactTop = new Pane();
            aboutContactTop.setPrefSize(1100, 110);
            Label judul = new Label("About Hi-Petani");
            judul.getStyleClass().add("judulAboutContact");
            judul.setLayoutX(125.0);
            judul.setLayoutY(41.0);
            aboutContactTop.getChildren().add(judul);

            // membuat pane untuk center container
            CenterAboutContactPane centerNew = new CenterAboutContactPane();

            if (source.getId().equalsIgnoreCase("about")) {
                // untuk animasi yang aktif
                about.setVisited(true);
                home.setVisited(false);
                contact.setVisited(true);
                topQuestions.setVisited(false);
                user.setVisited(false);

                // mengganti top main container
                questionsContainer.setTop(aboutContactTop);

                // mengganti center main container
                questionsContainer.setCenter(centerNew);
                centerNew.setHeightParent(815);

                // mengubah button menjadi null
                questionsContainer.setBottom(null);
            } else if (source.getId().equalsIgnoreCase("home")) {
                about.setVisited(false);
                home.setVisited(true);
                contact.setVisited(false);
                topQuestions.setVisited(false);
                user.setVisited(false);

                // mengubah judulnya
                questionsContainer.setTop(topQuestionsContainer);
                judulTopQuestionContainer.setText("All Questions");

                // menampilkan pertanyaan
                panelPertanyaan = new QuestionsContainerPane(false);
                questionsContainer.setCenter(panelPertanyaan);
                totalQuestions.setText(panelPertanyaan.getChildren().size() + " questions");
                // mengubah tinggi borderpane tegantung dari banyaknya jumlah pertanyaan
                panelPertanyaan.setHeightParent(panelPertanyaan.getChildren().size());

                // set sidebar
                panelKategori = new KategoriPane("All Questions");
                sidebar.getChildren().set(sidebar.getChildren().size() - 1, panelKategori);

                // mengubah button menjadi null
                questionsContainer.setBottom(null);
            } else if (source.getId().equalsIgnoreCase("contact")) {
                about.setVisited(true);
                home.setVisited(false);
                contact.setVisited(true);
                topQuestions.setVisited(false);
                user.setVisited(false);
                // mengganti top main container
                questionsContainer.setTop(aboutContactTop);

                // mengganti center main container
                questionsContainer.setCenter(centerNew);
                centerNew.setHeightParent(815);

                // mengubah button menjadi null
                questionsContainer.setBottom(null);
            } else if (source.getId().equalsIgnoreCase("topquestions")) {
                about.setVisited(false);
                home.setVisited(true);
                contact.setVisited(false);
                topQuestions.setVisited(true);
                user.setVisited(false);

                // mengubah judulnya
                questionsContainer.setTop(topQuestionsContainer);
                judulTopQuestionContainer.setText("Top Questions");


                // menampilkan pertanyaan
                panelPertanyaan = new QuestionsContainerPane(true);
                questionsContainer.setCenter(panelPertanyaan);
                totalQuestions.setText(panelPertanyaan.getChildren().size() + " questions");
                // mengubah tinggi borderpane tegantung dari banyaknya jumlah pertanyaan
                panelPertanyaan.setHeightParent(panelPertanyaan.getChildren().size());


                // set sidebar
                sidebar.getChildren().set(sidebar.getChildren().size() - 1, panelKategori);

                // mengubah button menjadi null
                questionsContainer.setBottom(null);
            } else if (source.getId().equalsIgnoreCase("user")) {
                about.setVisited(false);
                home.setVisited(false);
                contact.setVisited(false);
                topQuestions.setVisited(false);
                user.setVisited(true);

                // mengubah button menjadi null
                questionsContainer.setBottom(null);
            }
        }

    }

    /* methode yg akan pertama kali dipanggil ketika progran di run */
    @FXML
    public void initialize(){
        //custom login btn
        loginBtn = new Button("Log In");
        loginBtn.getStyleClass().add("loginBtn");
        loginBtn.setLayoutX(1050);
        loginBtn.setLayoutY(37);
        loginBtn.setOnMouseClicked(mouseEvent -> {
            loginOrRegis = new Stage();
            Scene scene = null;
            try {
                Parent root = FXMLLoader.load(App.class.getResource("loginView.fxml"));
                scene = new Scene(root, 400, 480);
                scene.getStylesheets().add(App.class.getResource("style/login.css").toExternalForm());
            }catch (Exception ex){ ex.printStackTrace(); }

            setLoginOrRegis(scene);

        });

        // checking apakah sudah login atau belum | dan custom header
        if(SistemLogin.isLogin()){
            user.setText(SistemLogin.username);
            // menghapus tombol login
            header.getChildren().remove(header.getChildren().size()-1);

            // menambahkan user dan poto komponen
            header.getChildren().add(user);
            header.getChildren().add(poto);
        }else{
            // menghapus komponen user dan poto, serta menambahkan tombol login
            header.getChildren().remove(header.getChildren().size()-1);
            header.getChildren().remove(header.getChildren().size()-1);
            header.getChildren().add(loginBtn);
        }

        // mengubah top
        questionsContainer.setTop(topQuestionsContainer);
        judulTopQuestionContainer.setText("All Questions");

        panelPertanyaan = new QuestionsContainerPane(false);
        questionsContainer.setCenter(panelPertanyaan);
        questionsContainer.setTop(topQuestionsContainer);
        // mengubah tinggi borderpane tegantung dari banyaknya jumlah pertanyaan
        panelPertanyaan.setHeightParent(panelPertanyaan.getChildren().size());

        home.setVisited(true);
        panelKategori = new KategoriPane("All Questions");
        sidebar.getChildren().set(sidebar.getChildren().size() - 1 , panelKategori);
        totalQuestions.setText(panelPertanyaan.getChildren().size() + " questions");

        // mengubah button menjadi null
        questionsContainer.setBottom(null);

    }

    public void setProfileHover(MouseEvent e){
        poto.setCursor(Cursor.HAND);
    }

    public void askingQuestion(MouseEvent ev) throws Exception{
        loginOrRegis = new Stage();

//        checking apakah sudah login atau belom
        if (SistemLogin.isLogin()) {
            // reset container
            questionsContainer.setTop(null);
            questionsContainer.setCenter(null);
            questionsContainer.setBottom(null);

            // adding to container
            AskingQuestionPane panelBertanya = new AskingQuestionPane();
            questionsContainer.setCenter(panelBertanya);
            questionsContainer.setPrefHeight(800);
        } else {
            Parent root = FXMLLoader.load(App.class.getResource("loginView.fxml"));
            Scene scene = new Scene(root, 400, 480);
            scene.getStylesheets().add(App.class.getResource("style/login.css").toExternalForm());
            setLoginOrRegis(scene);
        }
    }

    public void scrollPaneAction(Event e) {
        mainContainer.getSkin().getNode().setStyle("-fx-background-color: transparent;");

        // menelusuri elemen
        for (Node node : panelPertanyaan.getChildren()) {
            QuestionPane panelQuestion = (QuestionPane) node;
            // menambah action ketika button yang ada di objek dari QuestionPane di klik
            panelQuestion.getChildren().get(panelQuestion.getChildren().size()-1).setOnMouseClicked(this::kategoriAction);

            //menambah action ketika judul yang ada di objek dari QuestionPane di klik
            panelQuestion.getChildren().get(1).setOnMouseClicked(MouseEvent->{
                try {
                    linkClicked(MouseEvent);
                }catch (Exception ex){}
                KategoriPane panel = (KategoriPane) sidebar.getChildren().get(sidebar.getChildren().size()-1);
                for (Node nd: panel.getChildren()) {
                    ((CheckBox)nd).setSelected(false);
                }
                QuestionPane that = (QuestionPane) ((Hyperlink)MouseEvent.getSource()).getParent();
                // deklarasi object dan menempatkan untuk top property
                DetailQuestionPaneTop topPanel = new DetailQuestionPaneTop(that.idPertanyaan);
                questionsContainer.setTop(topPanel);


                // deklarasi object dan menempatkan untuk center property
                DetailQuestionPaneCenter centerPanel = new DetailQuestionPaneCenter(that.idPertanyaan);
                questionsContainer.setCenter(centerPanel);
                centerPanel.setHeightParent();

                // setting visual jumlah komentar
                totalComment = centerPanel.totalKomentar;
                topPanel.setCommentText(totalComment);

                // deklarasi object dan menempatkan untuk bottom property
                DetailQuestionPaneBottom bottomPanel = new DetailQuestionPaneBottom();
                questionsContainer.setBottom(bottomPanel);
                bottomPanel.setHeightParent();

                // set text area
                if(!valueComment.isBlank()){
                    bottomPanel.textArea.setText(valueComment);
                }

                // add event for post button
                bottomPanel.post.setOnMouseClicked(mouseEvent -> {

                    loginOrRegis = new Stage();
                    //checking apakah sudah login atau belom
                    if(SistemLogin.isLogin()){
                        valueComment = bottomPanel.textArea.getText();
                        if(!valueComment.isBlank()) {
                            // send comment to database
                            int infoUpdate = Setter.sendComment(valueComment, that.idPertanyaan, SistemLogin.userId);

                            if (infoUpdate == 0) {
                                modalAlert("Komentar gagal di Upload");
                            } else if (infoUpdate == -1) {
                                modalAlert("Kesalahan SQL Syntax");
                            } else if (infoUpdate == 1) {
                                modalAlert("Komentar berhasil di Upload");
                            }

                            // reset textArea
                            bottomPanel.textArea.setText(valueComment);
                        }else {
                            modalAlert("Komentar tidak boleh kosong");
                        }
                    }else{
                        valueComment = bottomPanel.textArea.getText();
                        Scene scene = null;
                        try {
                            Parent root = FXMLLoader.load(App.class.getResource("loginView.fxml"));
                            scene =new Scene(root, 400, 480);
                            scene.getStylesheets().add(App.class.getResource("style/login.css").toExternalForm());
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                        setLoginOrRegis(scene);
                    }
                });

                // add event for like button
                topPanel.getLike().setOnMouseClicked(mouseEvent->{
                    loginOrRegis = new Stage();
                    //checking apakah sudah login atau belom
                    if(SistemLogin.isLogin()){
                        Setter.sendLike(that.idPertanyaan);
                    }else{
                        Scene scene = null;
                        try {
                            Parent root = FXMLLoader.load(App.class.getResource("loginView.fxml"));
                            scene =new Scene(root, 400, 480);
                            scene.getStylesheets().add(App.class.getResource("style/login.css").toExternalForm());
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                        setLoginOrRegis(scene);
                    }
                });
            });
        }
    }

    private void setLoginOrRegis(Scene scene){
        loginOrRegis.setTitle("Login");
        loginOrRegis.setResizable(false);
        loginOrRegis.setScene(scene);
        loginOrRegis.initModality(Modality.APPLICATION_MODAL);
        loginOrRegis.setOnCloseRequest(Event -> {
            loginOrRegis.close();
            user.setText(SistemLogin.username);
            header.getChildren().add(user);
        });
        loginOrRegis.showAndWait();
        initialize();
    }

    private void modalAlert(String h){
        // deklarasi alert
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Information");
        info.setHeaderText(h);
        info.show();
        valueComment = "";
    }
}