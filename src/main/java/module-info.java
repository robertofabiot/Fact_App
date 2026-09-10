module com.example.fact_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens com.example.fact_app to javafx.fxml;
    opens com.example.fact_app.controller to javafx.fxml;
    opens com.example.fact_app.model to javafx.base, javafx.fxml;
    opens com.example.fact_app.application to javafx.graphics, javafx.fxml;

    exports com.example.fact_app;
    exports com.example.fact_app.application;
    exports com.example.fact_app.controller;
    exports com.example.fact_app.model;
    exports com.example.fact_app.util;
}