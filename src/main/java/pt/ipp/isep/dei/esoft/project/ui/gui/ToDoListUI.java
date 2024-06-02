package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.urgencyDegree;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpacesRepository;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

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
    public Button addTaskBtn;
    @FXML
    public Button returnBtn;
    @FXML
    public ListView showcaseListLst;

    @FXML
    public void returnToMenu(){
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
        //TODO: Verifications

        String reference = referenceTxt.getText();
        String description = descriptionTxt.getText();
        int duration = Integer.parseInt(durationTxt.getText());
        urgencyDegree urgencyDegree = (pt.ipp.isep.dei.esoft.project.domain.urgencyDegree) urgencyDegreeBox.getValue();

        GreenSpacesController greenSpacesController = new GreenSpacesController();
        GreenSpaces greenSpaces = greenSpacesController.getGreenSpaceByName(greenSpaceBox.getValue().toString());

        Optional<Task> optTask = controller.registerTask(reference, description, duration, urgencyDegree, greenSpaces);

        if (optTask.isPresent()) {
            System.out.println("Task registered successfully!");
            showcaseListLst.getItems().add(reference);
        } else {
            System.out.println("Task not registered!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<urgencyDegree> urgencyDegrees = List.of(urgencyDegree.LOW, urgencyDegree.MEDIUM, urgencyDegree.HIGH);
        ObservableList<urgencyDegree> urgencyDegreesObs = FXCollections.observableArrayList(urgencyDegrees);
        urgencyDegreeBox.setItems(urgencyDegreesObs);

        GreenSpacesController controller = new GreenSpacesController();
        List<String> greenSpaces = controller.getGreenSpacesNames();
        ObservableList<String> greenSpacesObs = FXCollections.observableArrayList(greenSpaces);
        greenSpaceBox.setItems(greenSpacesObs);

        CreateTaskController createTaskController = new CreateTaskController();
        List<String> tasks = createTaskController.getTasks();
        showcaseListLst.getItems().addAll(tasks);
    }
}
