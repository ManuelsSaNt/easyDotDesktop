module com.example.easypointdesktop {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.easypointdesktop to javafx.fxml;
    exports com.example.easypointdesktop;
}