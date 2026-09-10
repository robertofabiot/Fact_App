package com.example.fact_app.controller;

import com.example.fact_app.model.Cargo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CargoController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<Cargo> tblCargos;

    @FXML
    private TableColumn<Cargo, Integer> colId;

    @FXML
    private TableColumn<Cargo, String> colNombre;

    @FXML
    private TableColumn<Cargo, String> colDescripcion;

    private final ObservableList<Cargo> listaCargos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        tblCargos.setItems(listaCargos);

        tblCargos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtId.setText(newSelection.getId() != null ? newSelection.getId().toString() : "");
                txtNombre.setText(newSelection.getNombre() != null ? newSelection.getNombre() : "");
                txtDescripcion.setText(newSelection.getDescripcion() != null ? newSelection.getDescripcion() : "");
            }
        });
    }

    @FXML
    public void addOnClick(ActionEvent event) {
        try {
            if (txtId.getText() == null || txtId.getText().trim().isEmpty() ||
                txtNombre.getText() == null || txtNombre.getText().trim().isEmpty()) {
                mostrarAlerta("Validación", "Por favor complete al menos el ID y el Nombre.", Alert.AlertType.WARNING);
                return;
            }

            int id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText() != null ? txtDescripcion.getText().trim() : "";

            for (Cargo c : listaCargos) {
                if (c.getId() != null && c.getId() == id) {
                    mostrarAlerta("Error", "Ya existe un cargo con el ID: " + id, Alert.AlertType.ERROR);
                    return;
                }
            }

            Cargo nuevo = new Cargo(id, nombre, descripcion);
            listaCargos.add(nuevo);
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "El ID debe ser un número entero válido.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void editOnClick(ActionEvent event) {
        Cargo seleccionado = tblCargos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Aviso", "Seleccione un cargo de la tabla para editar.", Alert.AlertType.WARNING);
            return;
        }

        try {
            if (txtId.getText() == null || txtId.getText().trim().isEmpty() ||
                txtNombre.getText() == null || txtNombre.getText().trim().isEmpty()) {
                mostrarAlerta("Validación", "Por favor complete al menos el ID y el Nombre.", Alert.AlertType.WARNING);
                return;
            }

            int id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText() != null ? txtDescripcion.getText().trim() : "";

            // Si se cambió el id, asegurarse de que no colisione con otro
            for (Cargo c : listaCargos) {
                if (c != seleccionado && c.getId() != null && c.getId() == id) {
                    mostrarAlerta("Error", "Ya existe otro cargo con el ID: " + id, Alert.AlertType.ERROR);
                    return;
                }
            }

            seleccionado.setId(id);
            seleccionado.setNombre(nombre);
            seleccionado.setDescripcion(descripcion);

            tblCargos.refresh();
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "El ID debe ser un número entero válido.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void deleteOnClick(ActionEvent event) {
        Cargo seleccionado = tblCargos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Aviso", "Seleccione un cargo de la tabla para borrar.", Alert.AlertType.WARNING);
            return;
        }

        listaCargos.remove(seleccionado);
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        tblCargos.getSelectionModel().clearSelection();
    }

    @FXML
    public void onVolverMenuClick(ActionEvent event) {
        com.example.fact_app.util.SceneManager.switchScene("/com/example/fact_app/fxml/menu-principal.fxml", "Sistema de Facturación - Menú Principal");
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
