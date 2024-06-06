package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.console.DevTeamUI;
import pt.ipp.isep.dei.esoft.project.ui.console.authorization.AuthenticationUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class MainMenuUI implements Runnable {

    public MainMenuUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Do Login", new AuthenticationUI()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
        options.add(new MenuItem("Save", new Runnable() {
            public void run() {
                saveSkillRepository();
                saveJobRepository();
                saveCollaborators();
            }
        }));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- MAIN MENU --------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

    public void saveSkillRepository() {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFiles/skillRepository.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Repositories.getInstance().getSkillRepository());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void saveJobRepository() {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFiles/jobRepository.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Repositories.getInstance().getJobRepository());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void saveCollaborators() {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFiles/collaboratorRepository.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Repositories.getInstance().getCollaboratorRepository());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void saveUsers() {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFiles/authenticationRepository.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Repositories.getInstance().getAuthenticationRepository());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void saveOrganizations () {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFiles/organizationRepository.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Repositories.getInstance().getOrganizationRepository());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}