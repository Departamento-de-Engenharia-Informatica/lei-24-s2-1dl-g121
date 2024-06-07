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
    private ArrayList<Skill> requiredSkills;

    static Scanner sc = new Scanner(System.in);

    public CreateJobUI() {
        controller = new CreateJobController();
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


    private void submitData() {
        Optional<Job> job = getController().createJob(name);

        if (job.isPresent()) {
            System.out.println("\nJob successfully created!");
        } else {
            System.out.println("\nJob not created!");
        }
    }

    private void requestData() {

        //Request the Task Reference from the console
        name = requestName();

    }

    private String requestName() {
        String jobName;
        do {
            jobName = Utils.readLineFromConsole("Insert the job name: ");
        } while (!verifyName(jobName));

        return jobName;
    }

    private boolean verifyName(String string) {
        // Check if the string is empty
        if (string == null || string.trim().isEmpty()) {
            System.out.println("Name is empty or invalid!");
            return false;
        }

        // Check if the string contains special characters
        if (string.matches(".*[^a-zA-Zç ].*")) {
            System.out.println("Name can not contain special characters!");
            return false;
        }

        // If the string is not empty and does not contain special characters, return true
        return true;
    }
}
