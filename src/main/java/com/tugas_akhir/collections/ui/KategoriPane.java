package com.tugas_akhir.collections.ui;

import com.tugas_akhir.config.Getter;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;

public class KategoriPane extends Pane {
    public KategoriPane(String selectedCheckboxLabel){
        int jumlahKategori = 0;
        String[][] rawData = Getter.getAllKategori();
        for (String[] data : rawData) {
            String name = data[1].substring(0, 1).toUpperCase() + data[1].substring(1);
            CheckBox ch = new CheckBox(name);
            ch.setPrefSize(100,23);
            ch.setLayoutX(27.0);
            ch.setLayoutY((jumlahKategori * 30) + 3.0);
            ch.setMnemonicParsing(false);
            ch.setOnMouseClicked(mouseEvent -> {
                CheckBox active =  (CheckBox) mouseEvent.getSource();
                for (Node node: this.getChildren()) {
                    CheckBox nodeCt = (CheckBox) node;
                    if(nodeCt.getText().equalsIgnoreCase(active.getText())){
                        nodeCt.setSelected(true);
                    }else {
                        nodeCt.setSelected(false);
                    }
                }
            });
            addChildrenAll(ch);
            jumlahKategori++;
        }

        CheckBox ch = new CheckBox("All Questions");
        ch.setPrefSize(200,23);
        ch.setLayoutX(27.0);
        ch.setLayoutY((jumlahKategori * 30) + 3.0);
        ch.setMnemonicParsing(false);
        ch.setOnMouseClicked(mouseEvent -> {
            CheckBox active =  (CheckBox) mouseEvent.getSource();
            for (Node node: this.getChildren()) {
                CheckBox nodeCt = (CheckBox) node;
                if(nodeCt.getText().equalsIgnoreCase(active.getText())){
                    nodeCt.setSelected(true);
                }else {
                    nodeCt.setSelected(false);
                }
            }
        });
//        ch.setOnMouseClicked();
        addChildrenAll(ch);

        this.setPrefSize(194,30.0);
        this.setLayoutX(54.0);
        this.setLayoutY(171.0);
        setSelectedCheckbox(selectedCheckboxLabel);
    }

    public KategoriPane(){
        int jumlahKategori = 0;
        String[][] rawData = Getter.getAllKategori();
        for (String[] data : rawData) {
            String name = data[1].substring(0, 1).toUpperCase() + data[1].substring(1);
            CheckBox ch = new CheckBox(name);
            ch.setPrefSize(100,23);
            ch.setLayoutX(27.0);
            ch.setLayoutY((jumlahKategori * 30) + 3.0);
            ch.setMnemonicParsing(false);

            addChildrenAll(ch);
            jumlahKategori++;
        }

        CheckBox ch = new CheckBox("All Questions");
        ch.setPrefSize(200,23);
        ch.setLayoutX(27.0);
        ch.setLayoutY((jumlahKategori * 30) + 3.0);
        ch.setMnemonicParsing(false);
        ch.setOnMouseClicked(mouseEvent -> {
            CheckBox active =  (CheckBox) mouseEvent.getSource();
            for (Node node: this.getChildren()) {
                CheckBox nodeCt = (CheckBox) node;
                if(nodeCt.getText().equalsIgnoreCase(active.getText())){
                    nodeCt.setSelected(true);
                }else {
                    nodeCt.setSelected(false);
                }
            }
        });
//        ch.setOnMouseClicked();
        addChildrenAll(ch);

        this.setPrefSize(194,30.0);
        this.setLayoutX(54.0);
        this.setLayoutY(171.0);
    }

    // methode untuk mensetting selected pada checkbox
    public void setSelectedCheckbox(String name){
        for (Node node: this.getChildren()) {
            CheckBox nodeCt = (CheckBox) node;
            if(nodeCt.getText().equalsIgnoreCase(name)){
                nodeCt.setSelected(true);
            }
        }
    }


    private boolean addChildrenAll(CheckBox ...childrens){
        try {
            return this.getChildren().addAll(childrens);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
