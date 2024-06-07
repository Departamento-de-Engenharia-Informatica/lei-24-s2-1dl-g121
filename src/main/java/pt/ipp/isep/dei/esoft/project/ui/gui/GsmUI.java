package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpacesController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GsmUI implements Initializable {

    @FXML
    public ListView<String> greenSpacesList;
    @FXML
    public Button addTaskBtn;
    @FXML
    public Button addEntryBtn;
    @FXML
    public Button createGreenSpaceBtn;
    @FXML
    public Button completeTaskBtn;

    @FXML
    public ListView<String> toDoListLst;
    @FXML
    public ListView<String> agendaLst;

    @FXML
    public Label errorMessageLbl;

    private GreenSpacesController greenSpacesController;
    private String loggedInUserEmail; // Field to store the logged-in user's email

    public GsmUI() {
        this.greenSpacesController = new GreenSpacesController();
    }

    // Method to set the logged-in user's email
    public void setLoggedInUserEmail(String email) {
        this.loggedInUserEmail = email;
        displayGreenSpaces(); // Call displayGreenSpaces after setting the email
    }

    @FXML
    private void displayGreenSpaces() {
        if (greenSpacesController != null && loggedInUserEmail != null) {
            greenSpacesList.getItems().clear(); // Clear the list before adding new items
            List<String> greenSpacesDetails = greenSpacesController.getGreenSpacesNamesAndEmailsByEmail(loggedInUserEmail);
            greenSpacesList.getItems().addAll(greenSpacesDetails);
        }
    }

    @FXML
    public void runAddEntry() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddEntryUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) addTaskBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToCreateGreenSpaceMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GreenSpacesUI.fxml"));
            Scene greenSpacesScene = new Scene(loader.load());
            GreenSpacesUI greenSpacesUI = loader.getController();
            greenSpacesUI.setController(new GreenSpacesController());
            Stage stage = (Stage) createGreenSpaceBtn.getScene().getWindow();
            stage.setScene(greenSpacesScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void runAddTask() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddTaskUI.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) addTaskBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToDoListController controller = new ToDoListController();
        List<String> tasks = controller.presentTasks();
        toDoListLst.getItems().addAll(tasks);

        AgendaController agendaController = new AgendaController();
        List<String> entries = agendaController.presentEntries();
        agendaLst.getItems().addAll(entries);

        // Moved displayGreenSpaces() call to setLoggedInUserEmail()
    }
}
