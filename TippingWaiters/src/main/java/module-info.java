module com.example.tippingwaiters {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.tippingwaiters to javafx.fxml;
    exports com.example.tippingwaiters;
}