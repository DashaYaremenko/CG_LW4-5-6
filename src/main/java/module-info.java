module com.example.cg_lw456 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cg_lw456 to javafx.fxml;
    exports com.example.cg_lw456;
}