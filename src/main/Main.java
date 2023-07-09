package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.JDBC;

/**
 * The main class.
 */
public class Main extends Application {
    /**
     * Opens and closes JDBC connection and launches JavaFX application.
     *
     * @param args The arguments.
     */
    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

    /**
     * Sets the scene to the login view.
     *
     * @param stage The stage.
     * @throws Exception from FXMLLoader.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        stage.setTitle("Client Scheduler");
        stage.setScene(new Scene(root));
        stage.show();
    }
}