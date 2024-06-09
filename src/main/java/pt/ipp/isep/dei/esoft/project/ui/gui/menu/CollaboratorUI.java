package pt.ipp.isep.dei.esoft.project.ui.gui.menu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CollaboratorUI implements Initializable {
    private String loggedInUserEmail;
    private Team userTeam;

    @FXML
    public ListView<String> entriesLst;

    @FXML
    public DatePicker startDate;
    @FXML
    public DatePicker endDate;

    @FXML
    private void dateChoosen() {
        boolean valid = true;
        Date start = null;
        Date end = null;

        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        Collaborator collaborator = collaboratorRepository.getCollaboratorByEmail(loggedInUserEmail);
        userTeam = collaborator.getTeam();

        try{
            start = java.sql.Date.valueOf(startDate.getValue());
        }catch (Exception e){
            valid = false;
        }

        try{
            end = java.sql.Date.valueOf(endDate.getValue());
        }catch (Exception e){
            valid = false;
        }

        if (valid) {
            AgendaController agendaController = new AgendaController();
            List<String> entries = agendaController.presentEntriesByTeamAndDateRange(userTeam,start,end);
            entriesLst.getItems().clear();
            entriesLst.getItems().addAll(entries);
        }
    }

    public void setLoggedInUserEmail(String email) {
        this.loggedInUserEmail = email;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //empty
    }
}
