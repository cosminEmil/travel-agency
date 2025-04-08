module org.example.newprojectmpp {
    requires java.sql;
    requires org.apache.logging.log4j;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.newprojectmpp to javafx.fxml;
    exports org.example.newprojectmpp;
}