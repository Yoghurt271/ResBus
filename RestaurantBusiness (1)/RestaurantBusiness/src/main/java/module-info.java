module com.example.restaurantbusiness {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.poi.poi;


    opens com.example.restaurantbusiness to javafx.fxml;
    exports com.example.restaurantbusiness;
}