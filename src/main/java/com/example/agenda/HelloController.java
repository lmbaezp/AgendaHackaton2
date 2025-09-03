package com.example.agenda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label tryText;
    private Label label;

    private Agenda agenda = new Agenda(5);
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onTryButtonClick(){
        tryText.setText("Esto es una prueba de botón");
    }

    @FXML
    protected void onBotonClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acción");
        alert.setHeaderText(null);
        alert.setContentText("¡Botón presionado!");
        alert.showAndWait();
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
                Contacto c = new Contacto(datos[0].trim(), datos[1].trim(),datos[2].trim());
                if (agenda.addContact(c)) {
                    mostrarInfo("Contacto agregado: " + c);
                } else {
                    mostrarError("No se pudo agregar. Agenda llena o contacto ya existe.");
                }
            } else {
                mostrarError("Formato incorrecto. Usa: nombre,apellido y telefono");
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
            mostrarInfo("Existe: " + agenda.exists(nombre, apellido));
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
        if (agenda.getContacts().isEmpty()) {
            mostrarInfo("No hay contactos.");
        } else {
            StringBuilder sb = new StringBuilder("Contactos:\n");
            for (Contacto c : agenda.getContacts()) {
                sb.append(c).append("\n");
            }
            mostrarInfo(sb.toString());
        }
    }

    public void onBuscar(ActionEvent actionEvent) {
        String nombre = pedirDato("Buscar contacto", "Nombre:");
        String apellido = pedirDato("Buscar contacto", "Apellido:");
        if (nombre != null && apellido != null) {
            Contacto c = agenda.search(nombre, apellido);
            if (c != null) {
                mostrarInfo("Encontrado: " + c);
            } else {
                mostrarError("No encontrado.");
            }
        }
    }

    public void onEliminar(ActionEvent actionEvent) {
        String nombre = pedirDato("Eliminar contacto", "Nombre:");
        String apellido = pedirDato("Eliminar contacto", "Apellido:");
        if (nombre != null && apellido != null) {
            if (agenda.delete(nombre, apellido)) {
                mostrarInfo("Eliminado.");
            } else {
                mostrarError("No encontrado.");
            }
        }
    }

    public void onTelefono(ActionEvent actionEvent) {
        String nombre = pedirDato("Modificar teléfono", "Nombre:");
        String apellido = pedirDato("Modificar teléfono", "Apellido:");
        String telefono = pedirDato("Modificar teléfono", "Nuevo número:");
        if (nombre != null && apellido != null && telefono != null) {
            Contacto c = agenda.search(nombre, apellido);
            if (c != null) {
                try {
                    c.setTelefono(telefono);
                    mostrarInfo("Teléfono actualizado: " + c);
                } catch (IllegalArgumentException e) {
                    mostrarError(e.getMessage());
                }
            } else {
                mostrarError("Contacto no encontrado.");
            }
        }
    }

    public void onAgendaLlena(ActionEvent actionEvent) {
        mostrarInfo("Agenda llena: " + agenda.isFull());
    }

    public void onEspacio(ActionEvent actionEvent) {
        mostrarInfo("Espacio disponible: " + agenda.freeSpace());
    }
}
