module com.sqlitegui {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sqlitegui to javafx.fxml;
    opens com.sqlitegui.controller to javafx.fxml;
    exports com.sqlitegui;
}
