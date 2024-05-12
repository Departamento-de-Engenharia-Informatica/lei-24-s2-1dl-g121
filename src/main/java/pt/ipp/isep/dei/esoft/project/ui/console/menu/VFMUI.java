package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class VFMUI implements Runnable {
    public VFMUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- Vehicle Fleet Manager MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}