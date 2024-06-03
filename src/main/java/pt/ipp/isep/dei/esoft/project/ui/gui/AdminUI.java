package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminUI implements Initializable {

    @FXML
    public Button addTaskBtn;

    @FXML
    public ListView<String> toDoListLst;

    @FXML
    public Button createGreenSpaceBtn;

    @FXML
    public void goToCreateGreenSpaceMenu(){
        try {
            // Load the AuthenticationUI FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GreenSpacesUI.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded parent root
            Scene scene = new Scene(root);

            // Get the current stage from one of your components (getScene in this case)
            Stage stage = (Stage) addTaskBtn.getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void runAddTask() {
        try {
            // Load the AuthenticationUI FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddTaskUI.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded parent root
            Scene scene = new Scene(root);

            // Get the current stage from one of your components (getScene in this case)
            Stage stage = (Stage) addTaskBtn.getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CreateTaskController controller = new CreateTaskController();
        List<String> tasks = controller.getTasks();
        toDoListLst.getItems().addAll(tasks);
    }

}
