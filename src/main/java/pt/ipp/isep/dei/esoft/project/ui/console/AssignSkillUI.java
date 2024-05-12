package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AssignSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;

public class AssignSkillUI implements Runnable {

    private AssignSkillController controller;

    public AssignSkillUI() {
        controller = new AssignSkillController();
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Assign Skills ------------------------");

        assignSkill();
    }

    public void assignSkill() {
        presentCollabratorList();
        String ID;
        do {
            ID = Utils.readLineFromConsole("Enter the selected Collaborator's ID:");
            if (controller.verifyCollaboratorById(ID)) {
                System.out.println("Collaborator not found!");
                System.out.println("Please enter a valid ID.");
            } else break;
        }while (true);

        int numberOfSkills = Utils.readIntegerFromConsole("Enter the number of skills to be assigned: ");

        presentSkillList();
        for (int i = 0; i < numberOfSkills; i++) {
            String skillName;
            do{
                skillName = Utils.readLineFromConsole("Enter the Skill's name:");
                if (controller.verifySkillByName(skillName)) {
                    System.out.println("Skill not found!");
                    System.out.println("Please enter a valid Skill name.");
                } else break;
            }while(true);

            controller.assignSkill(ID, skillName);
        }

        for(Collaborator collaborator : controller.getCollaboratorList()){
            if(collaborator.getIdentificationDocument().equals(ID)){
                System.out.println("Skills assigned to " + collaborator.getName() + " successfully!");
                System.out.println("Skills:");
                for(Skill skill : collaborator.getSkillList()){
                    System.out.println(skill.getName());
                }
            }
        }
    }

    public void presentCollabratorList(){
        System.out.println("Collaborators:");
        List<Collaborator> collaboratorList = controller.getCollaboratorList();
        for (Collaborator collaborator : collaboratorList){
            System.out.print(collaborator.getName());
            System.out.print(" > ID: ");
            System.out.println(collaborator.getIdentificationDocument());
        }
    }

    public void presentSkillList(){
        System.out.println("Skills:");
        List<Skill> skillList = controller.getSkillList();
        for (Skill skill : skillList){
            System.out.println(skill.getName());
        }
    }
}
