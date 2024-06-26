package pt.ipp.isep.dei.esoft.project.ui.gui.authorization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.gui.menu.CollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.gui.menu.GsmUI;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AuthenticationUI implements Initializable {
    private AuthenticationController controller;

    @FXML
    public Button loginBtn;

    @FXML
    public Button closeBtn;

    @FXML
    public TextField emailTxt;

    @FXML
    public PasswordField passwordTxt;

    @FXML
    public Label messageLbl;
    private String loggedInUserEmail;

    public AuthenticationUI() {
        controller = new AuthenticationController();
    }

    @FXML
    public void doLogin() {
        messageLbl.setText("");

        String id = emailTxt.getText();
        String pwd = passwordTxt.getText();

        boolean success = controller.doLogin(id, pwd);
        if (!success) {
            messageLbl.setText("Invalid UserId and/or Password.");
            emailTxt.clear();
            passwordTxt.clear();
        } else {
            try {
                // Check the user's role and load the corresponding UI
                String role = controller.getUserRole(id); // Assume this method returns the role of the user
                setLoggedInUserEmail(id);

                String fxmlFile;
                switch (role) {
                    case "gsm":
                        fxmlFile = "/fxml/GsmUI.fxml";
                        break;
                    case "collaborator":
                        fxmlFile = "/fxml/CollaboratorUI.fxml";
                        break;
                    default:
                        messageLbl.setText("No menu available for this user's role. :(");
                        emailTxt.clear();
                        passwordTxt.clear();
                        return;
                }

                // Load the corresponding FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();

                // Pass the logged-in user email to the respective controller
                if ("gsm".equals(role)) {
                    GsmUI gsmUI = loader.getController();
                    gsmUI.setLoggedInUserEmail(loggedInUserEmail);
                }

                if ("collaborator".equals(role)) {
                    CollaboratorUI collaboratorUI = loader.getController();
                    collaboratorUI.setLoggedInUserEmail(loggedInUserEmail);
                }

                // Create a new scene with the loaded parent root
                Scene scene = new Scene(root);

                // Get the current stage from one of your components (emailTxt in this case)
                Stage stage = (Stage) emailTxt.getScene().getWindow();

                // Set the new scene to the stage
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLoggedInUserEmail(String email) {
        this.loggedInUserEmail = email;
        if (emailTxt != null) {
            emailTxt.setText(email);
        }
    }


    @FXML
    public void closeLoginMenu() {
        try {
            // Load the MainMenuUI FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuUI.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded parent root
            Scene scene = new Scene(root);

            // Get the current stage from one of your components (closeBtn in this case)
            Stage stage = (Stage) emailTxt.getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //empty
    }
}