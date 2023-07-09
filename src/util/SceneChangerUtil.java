package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.Main;
import java.io.IOException;

/**
 * Utility class for changing scenes.
 */
public class SceneChangerUtil {

    /**
     * Changes scenes.
     *
     * @param event The ActionEvent to get the stage from.
     * @param resource The new scene to change to.
     * @throws IOException from FXMLLoader.
     */
    public static void changeScene(ActionEvent event, String resource) throws IOException {
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(Main.class.getResource(resource));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}