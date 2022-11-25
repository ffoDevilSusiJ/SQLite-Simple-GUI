module com.sqlitegui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.sqlitegui.model.data to java.sql;
    opens com.sqlitegui to javafx.fxml;
    opens com.sqlitegui.controller to javafx.fxml;
    exports com.sqlitegui;
}
