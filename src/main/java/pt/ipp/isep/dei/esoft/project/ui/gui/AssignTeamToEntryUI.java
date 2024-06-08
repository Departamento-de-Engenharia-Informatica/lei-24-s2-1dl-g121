package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AssignTeamToEntryUI implements Initializable {
    @FXML
    public ComboBox<String> teamBox;

    @FXML
    public ListView<String> agendaLst;
    @FXML
    public ListView<String> teamMembersLst;

    @FXML
    public Button assignTeamBtn;
    @FXML
    public Button returnBtn;

    @FXML
    public Label teamErrorLbl;
    @FXML
    public Label entryErrorLbl;

    @FXML
    public void assignTeamToEntry() {
        boolean valid = true;

        teamErrorLbl.setText("");
        entryErrorLbl.setText("");

        String teamName = "";
        if (teamBox.getValue() == null) {
            teamErrorLbl.setText("Must select a team!");
            valid = false;
        } else {
            teamName = teamBox.getValue();
        }

        String entry;
        String entryReference = "";
        try {
            entry = agendaLst.getSelectionModel().getSelectedItem();
            String[] parts = entry.split(" - ");
            entryReference = parts[0];
        } catch (NullPointerException e) {
            entryErrorLbl.setText("Must select an entry!");
            valid = false;
        }


        if (valid) {
            AgendaController agendaController = new AgendaController();
            Agenda agenda = Repositories.getInstance().getAgenda();
            if (agenda.getEntryByID(entryReference).getTeam() != null) {
                //Entry already has a team
                entryErrorLbl.setText("Entry already has a team assigned!");
            } else {
                agendaController.assignTeamToEntry(teamName, entryReference);
                reloadPage();
            }
        }
    }

    @FXML
    private void showMembers() {
        TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();
        String teamName = teamBox.getValue();
        List<String> members = teamRepository.getCollaboratorsNames(teamName);
        teamMembersLst.getItems().clear();
        teamMembersLst.getItems().addAll(members);
    }

    private void reloadPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AssignTeamToEntry.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) teamBox.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void returnToMenu() {
        try {
            // Load the AuthenticationUI FXML file
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AuthenticationUI.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GsmUI.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded parent root
            Scene scene = new Scene(root);

            // Get the current stage from one of your components (doLoginBtn in this case)
            Stage stage = (Stage) returnBtn.getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teamErrorLbl.setText("");
        entryErrorLbl.setText("");

        AgendaController agendaController = new AgendaController();
        List<String> entries = agendaController.presentEntries();
        agendaLst.getItems().addAll(entries);

        TeamRepository teamRepository = Repositories.getInstance().getTeamRepository();
        List<String> teams = teamRepository.getNames();
        teamBox.getItems().addAll(teams);
    }
}
