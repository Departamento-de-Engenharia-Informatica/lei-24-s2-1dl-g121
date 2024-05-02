package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterSkillsController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.Optional;
import java.util.Scanner;

/**
 * Register Skills UI (console).
 */
public class RegisterSkillsUI implements Runnable {

    private final RegisterSkillsController controller;
    private String skillName;

    public RegisterSkillsUI() {
        controller = new RegisterSkillsController();
    }

    private RegisterSkillsController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Register Skills ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Optional<Skill> skill = getController().registerSkill(skillName);

        if (skill.isPresent()) {
            System.out.println("\nSkill successfully registered!");
        } else {
            System.out.println("\nSkill not registered!");
        }
    }

    private void requestData() {
        skillName = requestSkillName();
    }

    private String requestSkillName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Skill Name: ");
        return input.nextLine();
    }
}
