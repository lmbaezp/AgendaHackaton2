module com.example.agenda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.agenda to javafx.fxml;
    exports com.example.agenda;
    exports com.example.agenda.model;
    opens com.example.agenda.model to javafx.fxml;
    exports com.example.agenda.controllers;
    opens com.example.agenda.controllers to javafx.fxml;

}