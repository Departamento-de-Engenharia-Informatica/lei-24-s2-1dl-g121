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
    public Button runBtn;

    @FXML
    public ChoiceBox<MenuItemGUI> chooseUserStoryBox;

    @FXML
    public void runUserStory() {
        MenuItemGUI selected = chooseUserStoryBox.getValue();
        if (selected != null) {
            String path = "/fxml/" + selected.getFxmlFileName() + ".fxml";
            System.out.println(path);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = (Stage) runBtn.getScene().getWindow(); // get the stage reference
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<MenuItemGUI> options = new ArrayList<>();
        options.add(new MenuItemGUI("GreenSpacesUI", "GreenSpacesUI"));
        options.add(new MenuItemGUI("View Existing Green Spaces", "ViewGreenSpaces"));


        chooseUserStoryBox.getItems().addAll(options);
    }

}
