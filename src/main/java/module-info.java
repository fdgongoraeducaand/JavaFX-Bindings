module com.example.javafxbindingsexamples {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxbindingsexamples to javafx.fxml;
    exports com.example.javafxbindingsexamples;
}