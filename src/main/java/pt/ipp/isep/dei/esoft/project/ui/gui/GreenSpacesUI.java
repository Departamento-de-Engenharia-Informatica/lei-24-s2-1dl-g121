package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.ui.gui.ViewGreenSpacesUI;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GreenSpacesUI implements Initializable {

    @FXML
    private ChoiceBox<String> greenSpaceType;
    @FXML
    private Label messageLbl;
    @FXML
    private TextField addressTxt;
    @FXML
    private TextField areaTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private Button submitBtn;
    @FXML
    private Button backBtn;

    private GreenSpacesController controller;
    private ViewGreenSpacesUI viewGreenSpacesUI;

    private List<String> validTypes = Arrays.asList("Garden", "Medium-sized Park", "Large-sized Park");

    // No-argument constructor
    public GreenSpacesUI() {
    }

    // Constructor with parameters
    public GreenSpacesUI(GreenSpacesController controller, ViewGreenSpacesUI viewGreenSpacesUI) {
        this.controller = controller;
        this.viewGreenSpacesUI = viewGreenSpacesUI;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        greenSpaceType.getItems().addAll(validTypes);
        messageLbl.setVisible(false); // Hide the message label initially
        submitBtn.setOnAction(event -> submitData()); // Set the button action to submitData method
        backBtn.setOnAction(event -> handleBackButtonAction()); // Set the back button action
    }

    @FXML
    private void submitData() {
        String type = greenSpaceType.getValue();
        String address = addressTxt.getText();
        double area;
        try {
            area = Double.parseDouble(areaTxt.getText());
        } catch (NumberFormatException e) {
            messageLbl.setText("Invalid area format.");
            messageLbl.setVisible(true);
            return;
        }
        String name = nameTxt.getText();
        String email = emailTxt.getText();

        // Validate email
        if (!validateEmail(email)) {
            messageLbl.setText("Invalid email format.");
            messageLbl.setVisible(true);
            return;
        }

        // Attempt to save the green space
        if (controller!= null) {
            Optional<GreenSpaces> createdGreenSpace = controller.createGreenSpace(type, area, address, name, email);
            if (createdGreenSpace!= null) {
                // Add the created green space to the controller
                controller.createGreenSpace(type, area, address, name, email);

                // Refresh the table in the ViewGreenSpacesUI
                if (viewGreenSpacesUI!= null) {
                    viewGreenSpacesUI.loadGreenSpaces(controller.getRuntimeGreenSpaces());
                }

                messageLbl.setText("Green space added successfully!");
                messageLbl.setVisible(true);
            }
        } else {
            messageLbl.setText("Controller is not initialized.");
            messageLbl.setVisible(true);
        }
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
    public List<GreenSpaces> getRuntimeGreenSpaces() {
        if (controller!= null) {
            return controller.getRuntimeGreenSpaces();
        } else {
            return new ArrayList<>();
        }
    }


    private boolean validateEmail(String email) {
        String regex = "^[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
