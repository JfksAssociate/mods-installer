module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.kordamp.bootstrapfx.core;
    requires java.datatransfer;
    requires java.desktop;
    requires zip4j;

    opens com.example.app to javafx.fxml;
    exports com.example.app;
}