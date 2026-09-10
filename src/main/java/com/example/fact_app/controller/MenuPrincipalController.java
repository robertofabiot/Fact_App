package com.example.fact_app.controller;

import com.example.fact_app.util.SceneManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class MenuPrincipalController {

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnCargos;

    @FXML
    private Button btnSalir;

    @FXML
    private MenuItem menuItemSalir;

    @FXML
    private MenuItem menuItemProductos;

    @FXML
    private MenuItem menuItemCargos;

    @FXML
    private MenuItem menuItemAcercaDe;

    @FXML
    public void onAbrirProductosClick(ActionEvent event) {
        SceneManager.switchScene("/com/example/fact_app/fxml/producto-view.fxml", "Módulo de Productos");
    }

    @FXML
    public void onAbrirCargosClick(ActionEvent event) {
        SceneManager.switchScene("/com/example/fact_app/fxml/cargo-view.fxml", "Módulo de Cargos");
    }

    @FXML
    public void onSalirClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void onAcercaDeClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Sistema de Facturación v1.0");
        alert.setContentText("Aplicación modular de facturación minorista desarrollada con JavaFX 21 y Maven.");
        alert.showAndWait();
    }
}
