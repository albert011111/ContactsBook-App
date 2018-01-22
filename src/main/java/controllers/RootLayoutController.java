package controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Patryk on 2017-08-15.
 */
public class RootLayoutController {


    public static final String BIRTHDAY_STATS_FXML = "/BirthdayStats.fxml";

    @FXML
    private void handleShowBirthdayStatistics() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(BIRTHDAY_STATS_FXML));
        Stage stage = new Stage();
        Parent root;
        try {
            root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Birthday Statistics");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
