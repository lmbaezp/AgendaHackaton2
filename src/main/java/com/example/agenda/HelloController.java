package com.example.agenda;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import java.util.Optional;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label tryText;
    private Label label;
    @FXML
    private BorderPane rootPane;

    private Agenda agenda = new Agenda(5);


@FXML
public void initialize() {
    FadeTransition fade = new FadeTransition(Duration.seconds(1.5), rootPane);
    fade.setFromValue(0);
    fade.setToValue(1);
    fade.play();
    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0),
                    new KeyValue(rootPane.backgroundProperty(),
                            new Background(new BackgroundFill(Color.web("#e3f2fd"), null, null)))),
            new KeyFrame(Duration.seconds(2),
                    new KeyValue(rootPane.backgroundProperty(),
                            new Background(new BackgroundFill(Color.web("#bbdefb"), null, null)))),
            new KeyFrame(Duration.seconds(4),
                    new KeyValue(rootPane.backgroundProperty(),
                            new Background(new BackgroundFill(Color.web("#e3f2fd"), null, null))))
    );
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.setAutoReverse(true);
    timeline.play();
}
    @FXML
    protected void onNuevo() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuevo contacto");
        dialog.setHeaderText("Añadir contacto");
        dialog.setContentText("Nombre, apellido y teléfono (separados por coma):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            String[] datos = input.split(",");
            if (datos.length == 3) {
                Contacto c = new Contacto(datos[0].trim(), datos[1].trim(), datos[2].trim());
                mostrarInfo(agenda.añadirContacto(c));
            } else {
                mostrarError("Formato incorrecto. Usa: nombre,apellido,telefono");
            }
        });
    }

    private void mostrarError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();
    }

    private void mostrarInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.showAndWait();
    }

    @FXML
    protected void onSalir() {
        System.exit(0);
    }

    public void onVerificar(ActionEvent actionEvent) {
        String nombre = pedirDato("Verificar contacto", "Nombre:");
        String apellido = pedirDato("Verificar contacto", "Apellido:");
        if (nombre != null && apellido != null) {
            Contacto c = new Contacto(nombre, apellido, "1234567");
            mostrarInfo("Existe: " + agenda.existeContacto(c));
        }
    }

    private String pedirDato(String titulo, String mensaje) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(titulo);
        dialog.setHeaderText(null);
        dialog.setContentText(mensaje);
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    public void onLista(ActionEvent actionEvent) {
        mostrarInfo(agenda.listarContactos());
    }

    public void onBuscar(ActionEvent actionEvent) {
        String nombre = pedirDato("Buscar contacto", "Nombre:");
        String apellido = pedirDato("Buscar contacto", "Apellido:");
        if (nombre != null && apellido != null) {
            mostrarInfo(agenda.buscaContacto(nombre, apellido));
        }
    }

    public void onEliminar(ActionEvent actionEvent) {
        String nombre = pedirDato("Eliminar contacto", "Nombre:");
        String apellido = pedirDato("Eliminar contacto", "Apellido:");
        if (nombre != null && apellido != null) {
            Contacto c = new Contacto(nombre, apellido, "1234567");
            mostrarInfo(agenda.eliminarContacto(c));
        }
    }

    public void onTelefono(ActionEvent actionEvent) {
        String nombre = pedirDato("Modificar teléfono", "Nombre:");
        String apellido = pedirDato("Modificar teléfono", "Apellido:");
        String telefono = pedirDato("Modificar teléfono", "Nuevo número:");
        if (nombre != null && apellido != null && telefono != null) {
            mostrarInfo(agenda.modificarTelefono(nombre, apellido, telefono));
        }
    }

    public void onAgendaLlena(ActionEvent actionEvent) {
        mostrarInfo(agenda.agendaLlena());
    }

    public void onEspacio(ActionEvent actionEvent) {
        mostrarInfo(agenda.espaciosLibres());
    }


}