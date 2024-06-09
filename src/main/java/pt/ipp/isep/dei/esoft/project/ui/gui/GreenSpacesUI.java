package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;

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
    private List<String> validTypes = Arrays.asList("Garden", "Medium-sized Park", "Large-sized Park");

    // No-argument constructor
    public GreenSpacesUI() {
    }
    public void setController(GreenSpacesController controller) {
        this.controller = controller;
    }

    public TextField getEmailTxt() {
        return emailTxt;
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
        if(controller != null) {
            Optional<GreenSpaces> createdGreenSpace = controller.createGreenSpace(type, area, address, name, email);
            if (createdGreenSpace.isPresent()) {
                // Add the created green space to the controller (if necessary)
               // controller.createGreenSpace(type, area, address, name, email);

                // Refresh the table in the ViewGreenSpacesUI
//            if (viewGreenSpacesUI != null) {
//                viewGreenSpacesUI.loadGreenSpaces(controller.getRuntimeGreenSpaces());
//            }

                messageLbl.setText("Green space added successfully!");
            }
            messageLbl.setVisible(true);
        }else{
            messageLbl.setText("Controller not intialized");
        }

    }


    @FXML
    private void handleBackButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GsmUI.fxml"));
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



    private boolean validateEmail(String email) {
        String regex = "^[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
