package com.example.fact_app.application;

import com.example.fact_app.util.SceneManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class FacturacionApplication extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.setPrimaryStage(stage);

        InputStream iconStream = getClass().getResourceAsStream("/com/example/fact_app/images/logo.png");
        if (iconStream != null) {
            stage.getIcons().add(new Image(iconStream));
        }

        SceneManager.switchScene("/com/example/fact_app/fxml/menu-principal.fxml", "Sistema de Facturación - Menú Principal");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
