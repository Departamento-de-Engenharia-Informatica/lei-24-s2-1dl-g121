package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.domain.status;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEntryUI implements Initializable {

    @FXML
    private ListView<String> agendaLst;

    @FXML
    private TextField duratuionTxt;

    @FXML
    private ComboBox<status> statusBox;

    @FXML
    private ComboBox<String> tasksBox;

    @FXML
    private Button addEntryBtn;

    @FXML
    private TextField entryIDTxt;

    @FXML
    private Button returnBtn;

    @FXML
    void addEntryToAgenda() {

    }

    @FXML
    void returnToMenu() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
