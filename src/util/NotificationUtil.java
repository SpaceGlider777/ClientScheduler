package util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * Utility class for displaying alerts.
 */
public class NotificationUtil {
    /**
     * Displays an alert.
     *
     * @param alertType The alert type.
     * @param title The title.
     * @param contentText The context text.
     */
    public static void displayAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation alert.
     *
     * @param title The title.
     * @param contentText The context text.
     * @return true if the user clicked OK, false otherwise.
     */
    public static boolean displayConfirmationAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(contentText);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
