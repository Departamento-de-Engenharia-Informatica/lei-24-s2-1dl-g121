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
    private int minSize;
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
        team = generateTeam(requiredSkills, maxSize, minSize);
        Optional<Team> createdTeam = getController().createTeam(requiredSkills, maxSize, minSize);

        if (createdTeam.isPresent()) {
            System.out.println("\nTeam successfully created!");
            System.out.println("Team members:");
            for (Collaborator collaborator : team) {
                System.out.println(collaborator.getName());
            }
        } else {
            System.out.println("\nTeam not created!");
        }
    }

    private void requestData() {

        //Request the Task Reference from the console
         maxSize = requestSize();

         minSize = requestMinSize();

        //Request the Task Description from the console
         numSkills = requestNumSkills();

        //Request the Task Informal Description from the console
        requiredSkills = requestRequiredSkills(numSkills);



    }

    private int requestMinSize() {
                minSize = Integer.parseInt(Utils.readLineFromConsole("What's the minimun size of your team?"));
            if(minSize<0||minSize > maxSize) {
                while (minSize < 0 || minSize > maxSize) {
                    System.out.println("Minimum size invalid");
                    minSize = Integer.parseInt(Utils.readLineFromConsole("What's the minimun size of your team?"));
                }
            }
        return minSize;
    }


    private int requestNumSkills() {
        int numberOfSkills;
        numberOfSkills = Utils.readIntegerFromConsole("How many skills are needed to this job?");
            if(numberOfSkills < 0) {
                while (numberOfSkills < 0) {
                    System.out.println("Number of Skills cannot be negative");
                    numberOfSkills = Utils.readIntegerFromConsole("How many skills are needed to this job?");
                }
            }

        return numberOfSkills;
    }

    private int requestSize() {
        maxSize = Integer.parseInt(Utils.readLineFromConsole("What's the max size of your team?"));
        if(maxSize < 0) {
            while (maxSize < 0) {
                maxSize = Integer.parseInt(Utils.readLineFromConsole("What's the max size of your team?"));
            }
        }
        return maxSize;
    }


    public ArrayList<Collaborator> generateTeam(ArrayList<Skill> requiredSkills, int maxSize, int minSize) {
            ArrayList<Collaborator> team = new ArrayList<>();

            List<Collaborator> availableCollaborators = controller.getCollaboratorsBySkills(requiredSkills);

            Collections.shuffle(availableCollaborators);

            for (Collaborator collaborator : availableCollaborators) {
                if (team.size() > maxSize || team.size() < minSize) {
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
