module com.example.airlines {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.airlines to javafx.fxml;
    exports com.example.airlines;
}