package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.*;

public class CreateTeamUI implements Runnable {

    private final CreateTeamController controller;
    private int maxSize;
    private int minSize;
    private int numSkills;
    private List<Skill> requiredSkills;

    private List<Collaborator> team = new ArrayList<>();

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
        Optional<Team> createdTeam = getController().createTeam(team);

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

        maxSize = requestSize();

        minSize = requestMinSize();

        numSkills = requestNumSkills();

        requiredSkills = requestRequiredSkills(numSkills);

    }

    private int requestMinSize() {
        minSize = -2;
        while (minSize < 0) {
            System.out.println("What's the minimum size of your team?");
            String input = sc.nextLine();
            try {
                minSize = Integer.parseInt(input);
                if (minSize < 0) {
                    System.out.println("Number cannot be negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return minSize;
    }


    private int requestNumSkills() {
        int numberOfSkills = -2;
        while (numberOfSkills < 0) {
            System.out.println("How many skills are needed for this job?");
            String input = sc.nextLine();
            try {
                numberOfSkills = Integer.parseInt(input);
                if (numberOfSkills < 0) {
                    System.out.println("Number of skills cannot be negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return numberOfSkills;
    }

    private int requestSize() {
        maxSize = -2;
        while (maxSize < 0) {
            System.out.println("What's the max size of your team?");
            String input = sc.nextLine();
            try {
                maxSize = Integer.parseInt(input);
                if (maxSize < 0) {
                    System.out.println("Max cannot be negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return maxSize;
    }


    public List<Collaborator> generateTeam(List<Skill> requiredSkills, int maxSize, int minSize) {
        List<Collaborator> allCollaborators = controller.getCollaboratorsBySkills(requiredSkills);
        List<Skill> remainingSkills = new ArrayList<>(requiredSkills);

        do{
            for (int i = 0; i < allCollaborators.size(); i++) {
                Collaborator collaborator = allCollaborators.get(i);
                for (int j = 0; j < remainingSkills.size(); j++) {
                    Skill skill = remainingSkills.get(i);
                    if (collaborator.getSkillList().contains(skill)) {
                        team.add(collaborator);
                        allCollaborators.remove(collaborator);
                        remainingSkills.remove(skill);
                    }
                }
            }

        }while (team.size() < minSize || team.size() > maxSize && !remainingSkills.isEmpty());

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


    public void presentSkillList() {
        System.out.println("Skills:");
        List<Skill> skillList = controller.getSkillList();
        for (Skill skill : skillList) {
            System.out.println(skill.getName());
        }
    }
}
