package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminGUI implements Initializable {

    @FXML
    public Button runBtn;

    @FXML
    public ChoiceBox chooseUserStoryBox;

    @FXML
    public void runUserStory(){
        MenuItem selected = (MenuItem) chooseUserStoryBox.getValue();
        selected.run();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register Skills", new RegisterSkillsUI()));
        options.add(new MenuItem("Register a new Job", new CreateJobUI()));
        options.add(new MenuItem("Register Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Assign a Skill to a Collaborator", new AssignSkillUI()));
        options.add(new MenuItem("Create a team", new CreateTeamUI()));

        chooseUserStoryBox.getItems().addAll(options);
    }
}
