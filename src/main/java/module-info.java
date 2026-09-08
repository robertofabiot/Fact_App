module com.example.fact_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jdk.jfr;

    opens com.example.fact_app to javafx.fxml;
    exports com.example.fact_app;
}