import database.dbutils.DbManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Patryk on 10.08.2017.
 */
public class AdressApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;


    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AdressApp");

        initRootLaout();
        showPersonOverview();


    }

    private void initRootLaout() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/PersonOverview.fxml"));
            AnchorPane anchorPane = loader.load();
            rootLayout.setCenter(anchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DbManager.getInstance().getConnection();
        launch(args);

    }
}
