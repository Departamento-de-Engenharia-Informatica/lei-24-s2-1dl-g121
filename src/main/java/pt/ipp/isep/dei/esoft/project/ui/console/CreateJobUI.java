package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CreateJobUI implements Runnable{
    private CreateJobController controller;
    private String name;
    private int numSkills;
    private ArrayList<Skill> requiredSkills;

    static Scanner sc = new Scanner(System.in);

    public CreateJobUI() {
        this.controller = controller;
    }
    private CreateJobController getController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n--- Create Job ------------------------");

        //taskCategoryDescription = displayAndSelectTaskCategory();

        requestData();

        submitData();
    }

    /*
     String job = Utils.readLineFromConsole("\"Hi! %nWhat's the name of the job you will register today?");
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
        Optional<Job> job = getController().createJob(name, numSkills, requiredSkills);

        if (job.isPresent()) {
            System.out.println("\nJob successfully created!");
        } else {
            System.out.println("\nJob not created!");
        }
    }

    private void requestData() {

        //Request the Task Reference from the console
        name = requestName();

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

    private String requestName() {
        String jobName = Utils.readLineFromConsole("What's the name of the job you will register today?");
        return jobName;
    }
}
