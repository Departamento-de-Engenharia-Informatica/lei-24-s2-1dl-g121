package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.RegisterCollaboratorUI;
import pt.ipp.isep.dei.esoft.project.ui.console.RegisterSkillsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ShowTextUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class HRMUI implements Runnable {
    public HRMUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Skills", new RegisterSkillsUI()));
        options.add(new MenuItem("Option 2", new ShowTextUI("You have chosen Option 2.")));
        options.add(new MenuItem("Register Collaborator", new RegisterCollaboratorUI()));
        options.add(new MenuItem("Option 4", new ShowTextUI("You have chosen Option 4.")));
        options.add(new MenuItem("Option 5", new ShowTextUI("You have chosen Option 5.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Human Resources Manager MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}