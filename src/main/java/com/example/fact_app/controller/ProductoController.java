package com.example.fact_app.controller;

import com.example.fact_app.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ProductoController {

    @FXML
    private Button btnVolverMenu;

    @FXML
    public void onVolverMenuClick(ActionEvent event) {
        SceneManager.switchScene("/com/example/fact_app/fxml/menu-principal.fxml", "Sistema de Facturación - Menú Principal");
    }
}
