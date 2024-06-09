package pt.ipp.isep.dei.esoft.project.ui.gui.menu;

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
import pt.ipp.isep.dei.esoft.project.ui.gui.GreenSpacesUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GsmUI implements Initializable {

    @FXML
    public Button removeGreenSpaceBtn;
    @FXML
    public Button addTaskBtn;
    @FXML
    public Button addEntryBtn;
    @FXML
    public Button createGreenSpaceBtn;
    @FXML
    public Button completeTaskBtn;
    @FXML
    public Button removeEntryBtn;
    @FXML
    public Button assignTeamBtn;

    @FXML
    public ListView<String> greenSpacesList;
    @FXML
    public ListView<String> toDoListLst;
    @FXML
    public ListView<String> agendaLst;

    @FXML
    public Label errorMessageLbl;
    @FXML
    public Label agendaMessageLbl;

    private GreenSpacesController greenSpacesController;
    private String loggedInUserEmail; // Field to store the logged-in user's email



    public GsmUI() {
        this.greenSpacesController = new GreenSpacesController();
    }

    public void setLoggedInUserEmail(String email) {
        this.loggedInUserEmail = email;
        displayGreenSpaces(); // Call displayGreenSpaces after setting the email
    }

    @FXML
    private void displayGreenSpaces() {
        if (greenSpacesController != null) {
            // && loggedInUserEmail != null
            greenSpacesList.getItems().clear(); // Clear the list before adding new items
            List<String> greenSpacesDetails = greenSpacesController.getSortedGreenSpacesNamesAndEmailsByEmail("gsm@this.app");
            greenSpacesList.getItems().addAll(greenSpacesDetails);
        }
    }

    @FXML
    private void removeSelectedGreenSpace() {
        errorMessageLbl.setText(""); // Clear any previous error message
        if (greenSpacesList.getSelectionModel().isEmpty()) {
            errorMessageLbl.setText("No green space selected!"); // Show error if no green space is selected
        } else {
            String selectedGreenSpace = greenSpacesList.getSelectionModel().getSelectedItem();
            String[] parts = selectedGreenSpace.split(" | ");
            String greenSpaceName = parts[0]; // Extract the name of the selected green space

            boolean success = greenSpacesController.removeGreenSpace(greenSpaceName);
            if (success) {
                // Refresh the green spaces list after removal
                displayGreenSpaces();
                errorMessageLbl.setText("Green space removed successfully.");
            } else {
                errorMessageLbl.setText("Failed to remove green space.");
            }
        }
    }


    @FXML
    private void assignTeamToEntry() {
        try {
            // Load the AuthenticationUI FXML file
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AuthenticationUI.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AssignTeamToEntry.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded parent root
            Scene scene = new Scene(root);

            // Get the current stage from one of your components (doLoginBtn in this case)
            Stage stage = (Stage) addTaskBtn.getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void removeEntry() {
        agendaMessageLbl.setText("");
        AgendaController agendaController = new AgendaController();
        if (agendaLst.getSelectionModel().getSelectedItem() == null){
            agendaMessageLbl.setText("No entry selected!");
        }else{
            String entry = agendaLst.getSelectionModel().getSelectedItem();
            String[] parts = entry.split(" - ");
            String entryReference = parts[0];
            agendaController.removeEntry(entryReference);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gsmUI.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) addTaskBtn.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    public void markTaskAsCompleted() {
        ToDoListController controller = new ToDoListController();
        if (toDoListLst.getSelectionModel().getSelectedItem() == null) {
            errorMessageLbl.setText("Please select a task");
        } else {
            errorMessageLbl.setText("");
            String taskAndGreenSpace = toDoListLst.getSelectionModel().getSelectedItem();
            String[] parts = taskAndGreenSpace.split(" - ");
            String taskReference = parts[0];
            controller.removeTask(taskReference);
            toDoListLst.getSelectionModel().clearSelection();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GsmUI.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) addTaskBtn.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        List<String> tasks = controller.presentTasks("gsm@this.app");
        toDoListLst.getItems().addAll(tasks);

        AgendaController agendaController = new AgendaController();
        List<String> entries = agendaController.presentEntries();
        agendaLst.getItems().addAll(entries);

        displayGreenSpaces();
    }

}
