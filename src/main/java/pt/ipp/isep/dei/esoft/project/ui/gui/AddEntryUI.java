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
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.status;
import pt.ipp.isep.dei.esoft.project.domain.urgencyDegree;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddEntryUI implements Initializable {
    @FXML
    public TextField entryIDTxt;

    @FXML
    public DatePicker dueDateDat;

    @FXML
    public ComboBox<String> tasksBox;
    @FXML
    public ComboBox<status> statusBox;

    @FXML
    public ListView<String> agendaLst;

    @FXML
    public Button addEntryBtn;
    @FXML
    public Button returnBtn;

    @FXML
    public Label errorMessageLbl;
    @FXML
    public Label successMessageLbl;


    @FXML
    void addEntryToAgenda() {
        errorMessageLbl.setText("");
        successMessageLbl.setText("");

        String entryID = "";
        Task task = null;
        Date dueDate = null;
        status status = null;

        boolean valid = true;
        do {
            entryID = entryIDTxt.getText();
            if (!validateEntryID(entryID)) {
                valid = false;
                break;
            }

            ToDoListController controller = new ToDoListController();
            task = controller.getTaskByReference(tasksBox.getValue());
            if (task == null){
                errorMessageLbl.setText("Please select a task");
                valid = false;
                break;
            }

            try {
                dueDate = java.sql.Date.valueOf(dueDateDat.getValue());
            }
            catch (Exception e){
                errorMessageLbl.setText("Please select a due date");
                valid = false;
                break;
            }

            status = statusBox.getValue();
            if (status == null){
                errorMessageLbl.setText("Please select a status");
                valid = false;
                break;
            }
        }while(false);

        if (valid) {
            AgendaController agendaController = new AgendaController();
            Optional<Entry> optTask = agendaController.registerEntry(entryID, task, dueDate, status);
            if (optTask.isPresent()) {
                errorMessageLbl.setText("");
                successMessageLbl.setText("Task registered successfully!");

                agendaLst.getItems().add(entryID);
            } else {
                successMessageLbl.setText("");
                errorMessageLbl.setText("Task not registered!");
            }
        }
    }

    private boolean validateEntryID(String entryID) {
        // Check if the string is empty
        if (entryID == null || entryID.trim().isEmpty()) {
            errorMessageLbl.setText("Please enter entryID!");
            return false;
        }

        // Check if the string contains special characters
        if (entryID.matches(".*[^a-zA-Z0-9ç ].*")) {
            errorMessageLbl.setText("EntryID can not contain special characters!");
            return false;
        }

        AgendaController controller = new AgendaController();
        List<String> entries = controller.getEntriesList();
        if (entries.contains(entryID)) {
            errorMessageLbl.setText("Already existing entryID!");

            return false;
        }

        // If the string is not empty and does not contain special characters, return true
        return true;
    }

    @FXML
    void returnToMenu() {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fill tasksBox
        ToDoListController controller = new ToDoListController();
        List<String> tasksList = controller.getTasks();
        ObservableList<String> tasksListObs = FXCollections.observableArrayList(tasksList);
        tasksBox.setItems(tasksListObs);

        // Fill statusBox
        List<status> statusList = List.of(status.DONE, status.CANCELED, status.PLANNED, status.POSTPONED);
        ObservableList<status> statusListObs = FXCollections.observableArrayList(statusList);
        statusBox.setItems(statusListObs);

        //Show current available entries
        AgendaController agendaController = new AgendaController();
        List<String> entries = agendaController.getEntriesList();
        ObservableList<String> entriesObs = FXCollections.observableArrayList(entries);
        agendaLst.setItems(entriesObs);
        // Disable focus on the list view
        agendaLst.setFocusTraversable(false);
        agendaLst.setCellFactory(
                lv -> new ListCell<String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item);
                            setMouseTransparent(true);
                        }
                    }
                });
    }
}
