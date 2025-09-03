package com.example.agenda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("¡Hola desde JavaFX!");
    }

    @FXML
    protected void onAgregarContacto() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("agregar-contactos.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        Stage stage = (Stage) ((Node) welcomeText).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Agregar Contacto");
    }

    @FXML
    protected void onVerContactos() {
        System.out.println("Ver contactos seleccionado");
    }


}
