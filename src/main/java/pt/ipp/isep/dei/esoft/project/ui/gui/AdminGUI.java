package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItemGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminGUI implements Initializable {

    @FXML
    public static Button runBtn;

    @FXML
    public ChoiceBox chooseUserStoryBox;

    @FXML
    public void runUserStory() {
        MenuItemGUI selected = (MenuItemGUI) chooseUserStoryBox.getValue();
        String Path = "/fxml/" + selected.toString().replace(" ", "") + ".fxml";
        System.out.println(Path);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Path));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) runBtn.getScene().getWindow(); // get the stage reference
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<MenuItemGUI> options = new ArrayList<>();
//        options.add(new MenuItem("Register Skills", new RegisterSkillsUI()));
//        options.add(new MenuItem("Register a new Job", new CreateJobUI()));
//        options.add(new MenuItem("Register Collaborator", new RegisterCollaboratorUI()));
//        options.add(new MenuItem("Assign a Skill to a Collaborator", new AssignSkillUI()));
//        options.add(new MenuItem("Create a team", new CreateTeamUI()));
        options.add(new MenuItemGUI("GreenSpacesUI", new GreenSpacesUI()));


        chooseUserStoryBox.getItems().addAll(options);
    }
}
