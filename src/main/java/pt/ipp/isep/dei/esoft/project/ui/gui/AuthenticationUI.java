package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.AdminUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VFMUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AuthenticationUI implements Initializable {
    private AuthenticationController ctrl;
    private Stage stage;

    @FXML
    public Button loginBtn;

    @FXML
    public TextField emailTxt;

    @FXML
    public PasswordField passwordTxt;

    @FXML
    public Label messageLbl;


    public AuthenticationUI() {
        ctrl = new AuthenticationController();
    }

//    public void run() {
//        boolean success = doLogin();
//
//        if (success) {
//            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
//            if ((roles == null) || (roles.isEmpty())) {
//                System.out.println("No role assigned to user.");
//            } else {
//                UserRoleDTO role = selectsRole(roles);
//                if (!Objects.isNull(role)) {
//                    List<MenuItem> rolesUI = getMenuItemForRoles();
//                    this.redirectToRoleUI(rolesUI, role);
//                } else {
//                    System.out.println("No role selected.");
//                }
//            }
//        }
//        this.logout();
//    }

    private List<MenuItem> getMenuItemForRoles() {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new AdminUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_HRM, new HRMUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_VFM, new VFMUI()));

        //TODO: Complete with other user roles and related RoleUI
        return rolesUI;
    }

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
                //initialize RoleUI
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
                }
            }
    }

    private void logout() {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                item.run();
            }
        }
        if (!found) {
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
        }
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //empty
    }
}
