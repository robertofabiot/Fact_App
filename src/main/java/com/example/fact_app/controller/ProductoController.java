package com.example.fact_app.controller;

import com.example.fact_app.model.Categoria;
import com.example.fact_app.model.Producto;
import com.example.fact_app.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;

public class ProductoController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<Categoria> cbCategoria;

    @FXML
    private TextField txtPrecioVenta;

    @FXML
    private TextField txtExistencia;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnVolver;

    @FXML
    private TableView<Producto> tblProductos;

    @FXML
    private TableColumn<Producto, Integer> colId;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Categoria> colCategoria;

    @FXML
    private TableColumn<Producto, BigDecimal> colPrecioVenta;

    @FXML
    private TableColumn<Producto, Integer> colExistencia;

    private final ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    private final ObservableList<Categoria> listaCategorias = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Carga de categorías iniciales
        listaCategorias.addAll(
                new Categoria(1, "Abarrotes", true),
                new Categoria(2, "Lácteos", true),
                new Categoria(3, "Bebidas", true),
                new Categoria(4, "Limpieza", true),
                new Categoria(5, "Snacks", true)
        );
        cbCategoria.setItems(listaCategorias);

        // Configuración de columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        colExistencia.setCellValueFactory(new PropertyValueFactory<>("existencia"));

        tblProductos.setItems(listaProductos);

        // Listener de selección en la tabla
        tblProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtId.setText(newSelection.getId() != null ? newSelection.getId().toString() : "");
                txtCodigo.setText(newSelection.getCodigo() != null ? newSelection.getCodigo() : "");
                txtNombre.setText(newSelection.getNombre() != null ? newSelection.getNombre() : "");
                cbCategoria.setValue(newSelection.getCategoria());
                txtPrecioVenta.setText(newSelection.getPrecioVenta() != null ? newSelection.getPrecioVenta().toString() : "");
                txtExistencia.setText(String.valueOf(newSelection.getExistencia()));
            }
        });
    }

    @FXML
    public void addOnClick(ActionEvent event) {
        try {
            if (txtId.getText() == null || txtId.getText().trim().isEmpty() ||
                txtCodigo.getText() == null || txtCodigo.getText().trim().isEmpty() ||
                txtNombre.getText() == null || txtNombre.getText().trim().isEmpty() ||
                cbCategoria.getValue() == null ||
                txtPrecioVenta.getText() == null || txtPrecioVenta.getText().trim().isEmpty() ||
                txtExistencia.getText() == null || txtExistencia.getText().trim().isEmpty()) {
                mostrarAlerta("Validación", "Por favor complete todos los campos requeridos.", Alert.AlertType.WARNING);
                return;
            }

            int id = Integer.parseInt(txtId.getText().trim());
            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            Categoria categoria = cbCategoria.getValue();
            BigDecimal precioVenta = new BigDecimal(txtPrecioVenta.getText().trim());
            int existencia = Integer.parseInt(txtExistencia.getText().trim());

            for (Producto p : listaProductos) {
                if (p.getId() != null && p.getId() == id) {
                    mostrarAlerta("Error", "Ya existe un producto con el ID: " + id, Alert.AlertType.ERROR);
                    return;
                }
                if (p.getCodigo() != null && p.getCodigo().equalsIgnoreCase(codigo)) {
                    mostrarAlerta("Error", "Ya existe un producto con el código: " + codigo, Alert.AlertType.ERROR);
                    return;
                }
            }

            Producto nuevo = new Producto(id, codigo, nombre, categoria, precioVenta, existencia, null, true);
            listaProductos.add(nuevo);
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "El ID y la Existencia deben ser números enteros, y el Precio un número decimal.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void editOnClick(ActionEvent event) {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Aviso", "Seleccione un producto de la tabla para editar.", Alert.AlertType.WARNING);
            return;
        }

        try {
            if (txtId.getText() == null || txtId.getText().trim().isEmpty() ||
                txtCodigo.getText() == null || txtCodigo.getText().trim().isEmpty() ||
                txtNombre.getText() == null || txtNombre.getText().trim().isEmpty() ||
                cbCategoria.getValue() == null ||
                txtPrecioVenta.getText() == null || txtPrecioVenta.getText().trim().isEmpty() ||
                txtExistencia.getText() == null || txtExistencia.getText().trim().isEmpty()) {
                mostrarAlerta("Validación", "Por favor complete todos los campos requeridos.", Alert.AlertType.WARNING);
                return;
            }

            int id = Integer.parseInt(txtId.getText().trim());
            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            Categoria categoria = cbCategoria.getValue();
            BigDecimal precioVenta = new BigDecimal(txtPrecioVenta.getText().trim());
            int existencia = Integer.parseInt(txtExistencia.getText().trim());

            for (Producto p : listaProductos) {
                if (p != seleccionado && p.getId() != null && p.getId() == id) {
                    mostrarAlerta("Error", "Ya existe otro producto con el ID: " + id, Alert.AlertType.ERROR);
                    return;
                }
                if (p != seleccionado && p.getCodigo() != null && p.getCodigo().equalsIgnoreCase(codigo)) {
                    mostrarAlerta("Error", "Ya existe otro producto con el código: " + codigo, Alert.AlertType.ERROR);
                    return;
                }
            }

            seleccionado.setId(id);
            seleccionado.setCodigo(codigo);
            seleccionado.setNombre(nombre);
            seleccionado.setCategoria(categoria);
            seleccionado.setPrecioVenta(precioVenta);
            seleccionado.setExistencia(existencia);

            tblProductos.refresh();
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "El ID y la Existencia deben ser números enteros, y el Precio un número decimal.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void deleteOnClick(ActionEvent event) {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Aviso", "Seleccione un producto de la tabla para borrar.", Alert.AlertType.WARNING);
            return;
        }

        listaProductos.remove(seleccionado);
        limpiarCampos();
    }

    @FXML
    public void limpiarOnClick(ActionEvent event) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtId.clear();
        txtCodigo.clear();
        txtNombre.clear();
        cbCategoria.setValue(null);
        txtPrecioVenta.clear();
        txtExistencia.clear();
        tblProductos.getSelectionModel().clearSelection();
    }

    @FXML
    public void onVolverMenuClick(ActionEvent event) {
        SceneManager.showHome();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
