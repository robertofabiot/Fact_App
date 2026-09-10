package com.example.fact_app.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneManager {

    private static Stage primaryStage;
    private static BorderPane rootLayout;
    private static Node homeView;

    private SceneManager() {
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setRootLayout(BorderPane layout) {
        rootLayout = layout;
    }

    public static BorderPane getRootLayout() {
        return rootLayout;
    }

    public static void setHomeView(Node view) {
        homeView = view;
    }

    public static void showHome() {
        if (rootLayout != null && homeView != null) {
            rootLayout.setCenter(homeView);
            if (primaryStage != null) {
                primaryStage.setTitle("Sistema de Facturación - Menú Principal");
            }
        } else {
            switchScene("/com/example/fact_app/fxml/menu-principal.fxml", "Sistema de Facturación - Menú Principal");
        }
    }

    public static void loadCenterView(String fxmlPath, String title) {
        if (rootLayout != null) {
            try {
                URL resource = SceneManager.class.getResource(fxmlPath);
                if (resource == null) {
                    throw new IllegalArgumentException("No se encontró el archivo FXML en: " + fxmlPath);
                }
                FXMLLoader loader = new FXMLLoader(resource);
                Parent view = loader.load();
                rootLayout.setCenter(view);
                if (primaryStage != null) {
                    primaryStage.setTitle(title);
                }
            } catch (IOException e) {
                System.err.println("Error al cargar la vista en el centro " + fxmlPath + ": " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            switchScene(fxmlPath, title);
        }
    }

    public static void switchScene(String fxmlPath, String title) {
        try {
            URL resource = SceneManager.class.getResource(fxmlPath);
            if (resource == null) {
                throw new IllegalArgumentException("No se encontró el archivo FXML en: " + fxmlPath);
            }
            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar la escena " + fxmlPath + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
