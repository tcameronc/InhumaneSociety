module com.mycompany.animalshelter1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.animalshelter1 to javafx.fxml;
    exports com.mycompany.animalshelter1;
}
