package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.*;

public class CreateTeamUI implements Runnable{

    private CreateTeamController controller;
    private int maxSize;
    private int numSkills;
    private ArrayList<Skill> requiredSkills;

    private ArrayList<Collaborator> team;

    static Scanner sc = new Scanner(System.in);

    public CreateTeamUI() {
        controller = new CreateTeamController();
    }
    private CreateTeamController getController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Create Team ------------------------");


        requestData();

        submitData();
    }


    private void submitData() {
        Optional<Team> team = getController().createTeam(maxSize, requiredSkills);

        if (team.isPresent()) {
            System.out.println("\nTeam successfully created!");
        } else {
            System.out.println("\nTeam not created!");
        }
    }

    private void requestData() {

        //Request the Task Reference from the console
         maxSize = requestSize();

        //Request the Task Description from the console
         numSkills = requestNumSkills();

        //Request the Task Informal Description from the console
        requiredSkills = requestRequiredSkills(numSkills);

        team = generateTeam(requiredSkills, maxSize);

        for(Collaborator teammate : team){
            System.out.println(teammate.getName());
        }


    }


    private int requestNumSkills() {
        int numberOfSkills = Utils.readIntegerFromConsole("How many skills are needed to this job?");
        return numberOfSkills;
    }

    private int requestSize() {
        int maxSize = Integer.parseInt(Utils.readLineFromConsole("What's the max size of your team?"));
        return maxSize;
    }


    public ArrayList<Collaborator> generateTeam(ArrayList<Skill> requiredSkills, int maxSize) {
            ArrayList<Collaborator> team = new ArrayList<>();

            List<Collaborator> availableCollaborators = controller.getCollaboratorsBySkills(requiredSkills);

            Collections.shuffle(availableCollaborators);

            for (Collaborator collaborator : availableCollaborators) {
                if (team.size() > maxSize) {
                    break;
                }
                team.add(collaborator);
            }

            return team;
        }


    private ArrayList<Skill> requestRequiredSkills(int numSkills) {
        presentSkillList();
        ArrayList<Skill> skillsNeeded = new ArrayList<>();
        int i = 0;
        while (i < numSkills) {
            String skillName;
            do {
                skillName = Utils.readLineFromConsole("Enter the Skill's name:");
                if (controller.verifySkillByName(skillName)) {
                    System.out.println("Skill not found!");
                    System.out.println("Please enter a valid Skill name.");
                } else {
                    Skill skill = new Skill(skillName);
                    skillsNeeded.add(skill);
                }
            } while (controller.verifySkillByName(skillName));
            i++;
        }
        return skillsNeeded;
    }


    public void presentSkillList(){
        System.out.println("Skills:");
        List<Skill> skillList = controller.getSkillList();
        for (Skill skill : skillList){
            System.out.println(skill.getName());
        }
    }
}
