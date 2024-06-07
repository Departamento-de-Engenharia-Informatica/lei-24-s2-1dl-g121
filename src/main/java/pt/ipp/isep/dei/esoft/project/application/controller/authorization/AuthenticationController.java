package pt.ipp.isep.dei.esoft.project.application.controller.authorization;

import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class AuthenticationController {

    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_HRM = "HUMAN RESOURCES MANAGER";
    public static final String ROLE_GSM = "GREEN SPACES MANAGER";
    public static final String ROLE_GSU = "GREEN SPACES USER";
    public static final String ROLE_VFM = "VEHICLE AND EQUIPMENT FLEET MANAGER";
    public static final String ROLE_QAM = "SOFTWARE QUALITY ASSESSMENT TEAM MANAGER";
    public static final String ROLE_COLLAB = "COLLABORATOR";


    //private final ApplicationSession applicationSession;
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationController() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    public boolean doLogin(String email, String pwd) {
        try {
            return authenticationRepository.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public List<UserRoleDTO> getUserRoles() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserSession().getUserRoles();
        }
        return null;
    }
    public String getUserRole(String email) {
        // Implement logic to get the user's role based on email
        // Return "admin", "gsm", or "collaborator" based on your application's logic
        if (email.equals("admin@this.app")) {
            return "admin";
        } else if (email.equals("gsm@this.app") || email.equals("gsm1@this.app") || email.equals("gsm2@this.app") || email.equals("gsm3@this.app")) {
            return "gsm";
        } else {
            return "collaborator";
        }
    }

    public void doLogout() {
        authenticationRepository.doLogout();
    }
}