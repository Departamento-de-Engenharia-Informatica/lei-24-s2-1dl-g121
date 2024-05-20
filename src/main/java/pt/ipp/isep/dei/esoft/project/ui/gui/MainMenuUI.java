package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainMenuUI implements Initializable {
    @FXML
    private Button doLoginBtn;
    @FXML
    private Button developmentTeamBtn;

    @FXML
    public void doLogin() {
        new AuthenticationUI().run();
    }

    @FXML
    public void showDevelopmentTeam() {
        new DevTeamUI().run();
    }

    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        // empty
    }
}
