package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CreateTeamUI implements Runnable{

    private CreateTeamController controller;
    private int maxSize;
    private int numSkills;
    private ArrayList<Skill> requiredSkills;

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

        //taskCategoryDescription = displayAndSelectTaskCategory();

        requestData();

        submitData();
    }

    /*
     String job = Utils.readLineFromConsole()"\"Hi! %nWhat's the name of the job you will register today?");
        int numberOfSkills = Utils.readIntegerFromConsole("How many skills are needed to this job?");
        String[] skills = new String[numberOfSkills];
        skillsNeeded(skills);
     */

//    private static void skillsNeeded(String[] skills) {
//        for (int i = 0; i < skills.length; i++) {
//            System.out.println("Type the skill:");
//            skills[i] = sc.next();
//        }
//    }

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

    }

    private ArrayList<Skill> requestRequiredSkills(int numSkills) {
        ArrayList<Skill> skills = new ArrayList<>();
        for (int i = 0; i < numSkills; i++) {
            System.out.println("Type the skill:");
            String skillName = sc.next();
            Skill skill = new Skill(skillName);
            skills.add(skill);
        }
        return skills;
    }

    private int requestNumSkills() {
        int numberOfSkills = Utils.readIntegerFromConsole("How many skills are needed to this job?");
        return numberOfSkills;
    }

    private int requestSize() {
        int maxSize = Integer.parseInt(Utils.readLineFromConsole("What's the max size of your team?"));
        return maxSize;
    }
}
