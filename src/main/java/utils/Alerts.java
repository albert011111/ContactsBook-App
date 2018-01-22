package utils;

import javafx.scene.control.Alert;

/**
 * Created by Patryk on 10.08.2017.
 */
public class Alerts {

    public static void warningAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Warning");
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static void informationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("INFO");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
