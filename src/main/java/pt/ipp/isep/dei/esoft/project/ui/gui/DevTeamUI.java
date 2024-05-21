package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Bruno Teixeira 1231091@isep.ipp.pt
 */
public class DevTeamUI implements Initializable {

    @FXML
    public Button closeBtn;

    @FXML
    public void closeDevMenu() {
        try {
            // Load the AuthenticationUI FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuUI.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded parent root
            Scene scene = new Scene(root);

            // Get the current stage from one of your components (closeBtn in this case)
            Stage stage = (Stage) closeBtn.getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // empty
    }
}
