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
    private TableView<GreenSpaces> GreenSpacessTable;
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
    private GreenSpacesUI greenSpacesUI;

    public void setGreenSpaceRegistry(GreenSpacesUI greenSpaceRegistry) {
        this.greenSpacesUI = greenSpaceRegistry;
    }

    @FXML
    public void initialize() {
        // Initialize table columns
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Populate table with data
        if (greenSpacesUI != null) {
            GreenSpacessTable.getItems().addAll(greenSpacesUI.getRuntimeGreenSpaces());
        } else {
            System.err.println("GreenSpaceRegistry is not set!");
        }
    }
    public void loadGreenSpaces(List<GreenSpaces> greenSpaces) {
        GreenSpacessTable.getItems().clear();
        GreenSpacessTable.getItems().addAll(greenSpaces);
    }
    @FXML
    private void handleBackButtonAction() {
        try {
            // Load AdminGUI.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminUI.fxml"));
            Scene adminScene = new Scene(loader.load());

            // Get the current stage
            Stage stage = (Stage) backBtn.getScene().getWindow();

            // Set the new scene (AdminGUI) to the stage
            stage.setScene(adminScene);
        } catch (IOException e) {
            e.printStackTrace();
            messageLbl.setText("Failed to load AdminGUI.");
            messageLbl.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
