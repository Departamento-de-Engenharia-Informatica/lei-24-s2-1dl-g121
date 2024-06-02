package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.urgencyDegree;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ToDoListUI implements Initializable {
    private final CreateTaskController controller = new CreateTaskController();

    @FXML
    public TextField referenceTxt;
    @FXML
    public TextField descriptionTxt;
    @FXML
    public TextField durationTxt;
    @FXML
    public ComboBox urgencyDegreeBox;
    @FXML
    public ComboBox greenSpaceBox;

    @FXML
    public Label errorMessageLbl;
    @FXML
    public Label successMessageLbl;

    @FXML
    public Button addTaskBtn;
    @FXML
    public Button returnBtn;
    @FXML
    public ListView showcaseListLst;

    @FXML
    public void returnToMenu() {
        try {
            // Load the AuthenticationUI FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminUI.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded parent root
            Scene scene = new Scene(root);

            // Get the current stage from one of your components (getScene in this case)
            Stage stage = (Stage) returnBtn.getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addTaskToToDoList() {
        errorMessageLbl.setText("");
        successMessageLbl.setText("");

        String reference = "";
        String description = "";
        int duration = 0;
        urgencyDegree urgencyDegree = null;
        GreenSpaces greenSpaces = null;

        boolean valid = true;
        do {
            reference = referenceTxt.getText();
            if (!verifyReference(reference)) {

                valid = false;
                break;
            }

            description = descriptionTxt.getText();
            if (!verifyDescription(description)) {
                errorMessageLbl.setText("Invalid description!");

                valid = false;
                break;
            }

            try {
                duration = Integer.parseInt(durationTxt.getText());
            } catch (NumberFormatException e) {
                errorMessageLbl.setText("Invalid duration!");
                valid = false;
                break;
            }
            if (!validateDuration(duration)) {
                errorMessageLbl.setText("Invalid duration!");

                valid = false;
                break;
            }

            urgencyDegree = (pt.ipp.isep.dei.esoft.project.domain.urgencyDegree) urgencyDegreeBox.getValue();
            if (urgencyDegree == null) {
                errorMessageLbl.setText("Must select urgency degree!");

                valid = false;
                break;
            }

            GreenSpacesController greenSpacesController = new GreenSpacesController();
            try {
                greenSpaces = greenSpacesController.getGreenSpaceByName(greenSpaceBox.getValue().toString());
            } catch (NullPointerException e) {
                errorMessageLbl.setText("Must select green space!");

                valid = false;
                break;
            }
        } while (false);



        if (valid) {
            Optional<Task> optTask = controller.registerTask(reference, description, duration, urgencyDegree, greenSpaces);
            if (optTask.isPresent()) {
                errorMessageLbl.setText("");
                successMessageLbl.setText("Task registered successfully!");

                showcaseListLst.getItems().add(reference);
            } else {
                successMessageLbl.setText("");
                errorMessageLbl.setText("Task not registered!");
            }
        }
    }

    private boolean verifyReference(String reference) {
        // Check if the string is empty
        if (reference == null || reference.trim().isEmpty()) {
            errorMessageLbl.setText("Invalid reference!");
            return false;
        }

        // Check if the string contains special characters
        if (reference.matches(".*[^a-zA-Z0-9ç ].*")) {
            errorMessageLbl.setText("Invalid reference!");
            return false;
        }

        CreateTaskController createTaskController = new CreateTaskController();
        List<String> tasks = createTaskController.getTasks();
        if (tasks.contains(reference)) {
            errorMessageLbl.setText("Already existing reference!");

            return false;
        }

        // If the string is not empty and does not contain special characters, return true
        return true;
    }

    private boolean validateDuration(int duration) {

        return duration > 0;
    }

    private boolean verifyDescription(String string) {
        // Check if the string is empty
        if (string == null || string.trim().isEmpty()) {
            return false;
        }

        // Check if the string contains special characters
        if (string.matches(".*[^a-zA-Zç ].*")) {
            return false;
        }

        // If the string is not empty and does not contain special characters, return true
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill urgencyDegreeBox
        List<urgencyDegree> urgencyDegrees = List.of(urgencyDegree.LOW, urgencyDegree.MEDIUM, urgencyDegree.HIGH);
        ObservableList<urgencyDegree> urgencyDegreesObs = FXCollections.observableArrayList(urgencyDegrees);
        urgencyDegreeBox.setItems(urgencyDegreesObs);

        // Fill greenSpaceBox
        GreenSpacesController controller = new GreenSpacesController();
        List<String> greenSpaces = controller.getGreenSpacesNames();
        ObservableList<String> greenSpacesObs = FXCollections.observableArrayList(greenSpaces);
        greenSpaceBox.setItems(greenSpacesObs);

        // Show current to do list
        CreateTaskController createTaskController = new CreateTaskController();
        List<String> tasks = createTaskController.getTasks();
        showcaseListLst.getItems().addAll(tasks);
    }
}
