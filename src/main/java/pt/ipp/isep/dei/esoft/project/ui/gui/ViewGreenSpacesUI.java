package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewGreenSpacesUI implements Initializable {

    @FXML
    private TableView<GreenSpaces> GreenSpacesTable;
    @FXML
    private TableColumn<GreenSpaces, String> typeColumn;
    @FXML
    private TableColumn<GreenSpaces, String> addressColumn;
    @FXML
    private TableColumn<GreenSpaces, Double> areaColumn;
    @FXML
    private TableColumn<GreenSpaces, String> nameColumn;
    @FXML
    private TableColumn<GreenSpaces, String> emailColumn;
    @FXML
    private Button backBtn;
    @FXML
    private Label messageLbl;
    @FXML
    private GreenSpacesUI greenSpacesUI;


    public void setGreenSpacesUI(GreenSpacesUI greenSpacesUI) {
        this.greenSpacesUI = greenSpacesUI;
    }

    public void loadGreenSpaces(List<GreenSpaces> greenSpaces) {
        GreenSpacesTable.getItems().clear();
        GreenSpacesTable.getItems().addAll(greenSpaces);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
