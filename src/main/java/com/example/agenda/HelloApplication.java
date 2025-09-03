package com.example.agenda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación JavaFX.
 * Extiende de Application y define el método start(), que es el punto de inicio
 * cuando se lanza la interfaz gráfica.
 */
public class HelloApplication extends Application {

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * @param stage ventana principal (Stage) de la aplicación.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Carga el archivo FXML que define la interfaz
        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("hello-view.fxml")
        );

        // Crea la escena principal con el FXML cargado y tamaño 600x400
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        // Vincula la hoja de estilos CSS a la escena
        scene.getStylesheets().add(
                getClass().getResource("styles.css").toExternalForm()
        );

        // Configuración de la ventana principal
        stage.setTitle("Agenda de Contactos"); // título en la barra
        stage.setScene(scene);                 // asigna la escena
        stage.show();                          // muestra la ventana
    }

    /**
     * Método main que lanza la aplicación JavaFX.
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch(); // inicia el ciclo de vida de JavaFX
    }
}