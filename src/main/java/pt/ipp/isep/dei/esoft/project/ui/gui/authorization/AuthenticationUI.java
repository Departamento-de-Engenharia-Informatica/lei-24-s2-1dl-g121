package pt.ipp.isep.dei.esoft.project.ui.gui.authorization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AuthenticationUI implements Initializable {
    private AuthenticationController ctrl;

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


    public AuthenticationUI() {
        ctrl = new AuthenticationController();
    }

//    private List<MenuItem> getMenuItemForRoles() {
//        List<MenuItem> rolesUI = new ArrayList<>();
//        rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new AdminUI()));
//        rolesUI.add(new MenuItem(AuthenticationController.ROLE_HRM, new HRMUI()));
//        rolesUI.add(new MenuItem(AuthenticationController.ROLE_VFM, new VFMUI()));
//        return rolesUI;
//    }

    @FXML
    public void doLogin() {
        boolean success = false;
        messageLbl.setText("");

            String id = emailTxt.getText();

            String pwd = passwordTxt.getText();

            success = ctrl.doLogin(id, pwd);
            if (!success) {
                messageLbl.setText("Invalid UserId and/or Password.");
                emailTxt.clear();
                passwordTxt.clear();
            }
            else{
                //sadly, we had to do this
                if(Objects.equals(id, "admin@this.app") && Objects.equals(pwd, "admin")){
                    try {
                        // Load the AuthenticationUI FXML file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminUI.fxml"));
                        Parent root = loader.load();

                        // Create a new scene with the loaded parent root
                        Scene scene = new Scene(root);

                        // Get the current stage from one of your components (getScene in this case)
                        Stage stage = (Stage) emailTxt.getScene().getWindow();

                        // Set the new scene to the stage
                        stage.setScene(scene);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(Objects.equals(id, "") && Objects.equals(pwd, "")){
                        try {
                            // Load the AuthenticationUI FXML file
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminUI.fxml"));
                            Parent root = loader.load();

                            // Create a new scene with the loaded parent root
                            Scene scene = new Scene(root);

                            // Get the current stage from one of your components (getScene in this case)
                            Stage stage = (Stage) emailTxt.getScene().getWindow();

                            // Set the new scene to the stage
                            stage.setScene(scene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    messageLbl.setText("No menu available for this user's role. :(");
                    emailTxt.clear();
                    passwordTxt.clear();
                }
            }
    }

    @FXML
    public void closeLoginMenu() {
        try {
            // Load the AuthenticationUI FXML file
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
