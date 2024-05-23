package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

public class Main {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        try {
            int option = Utils.readIntegerFromConsole("Run in: 1- Console, 2- JavaFx ");
            if(option == 1) {
                System.out.println("Running in Console");
                MainMenuUI menu = new MainMenuUI();
                menu.run();
            } else {
                System.out.println("Running in JavaFx");
                MainApp.main(args);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}