module com.example.agenda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.example.agenda to javafx.fxml;
    exports com.example.agenda;
}