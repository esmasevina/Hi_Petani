module com.tugas_akhir {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.tugas_akhir to javafx.fxml;
    exports com.tugas_akhir;

    opens com.tugas_akhir.collections.ui to javafx.fxml;
    exports com.tugas_akhir.collections.ui;
    exports com.tugas_akhir.collections.ui.detail;
    opens com.tugas_akhir.collections.ui.detail to javafx.fxml;
    exports com.tugas_akhir.controller;
    opens com.tugas_akhir.controller to javafx.fxml;
}