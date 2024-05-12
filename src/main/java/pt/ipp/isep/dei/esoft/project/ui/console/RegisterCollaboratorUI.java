package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Register Collaborator UI (console).
 */
public class RegisterCollaboratorUI implements Runnable {

    private final RegisterCollaboratorController controller;
    private static final Scanner scanner = new Scanner(System.in);



    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
    }

    private RegisterCollaboratorController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Register Collaborator ------------------------");

        registerCollaboratorData();
    }

    private void registerCollaboratorData() {
        Job job;
        do {
            presentJobList();
            System.out.println("Choose Job: ");
            String jobString = scanner.nextLine();
            job = controller.getJobByName(jobString);
            if (job == null) {
                System.out.println("Job not found!");
                return;
            } else break;
        } while (true);


        String name = requestName();
        String birthDetails = requestBirthDetails();
        String issuingDetails = requestIssuingDetails();
        String address = requestAddress();
        String phoneNumber = requestPhoneNumber();
        String email = requestEmail();
        String identificationDocument = requestIdentificationDocument();

        Optional<Collaborator> optCollaborator = controller.registerCollaborator(name, birthDetails, issuingDetails, address, phoneNumber, email, identificationDocument, job);
        if (optCollaborator.isPresent()) {
            System.out.println("Collaborator registered successfully!");
        } else {
            System.out.println("Collaborator not registered!");
        }
    }

    public void presentJobList(){
        System.out.println("Jobs:");
        List<Job> jobList = controller.getJobList();
        for (Job job : jobList){
            System.out.println(job.getName());
        }
    }

    private String requestName() {
        return Utils.readLineFromConsole("Enter Collaborator Name: ");
    }

    private String requestBirthDetails() {
        return Utils.readLineFromConsole("Enter Collaborator Birth Details: ");
    }

    private String requestIssuingDetails() {
        return Utils.readLineFromConsole("Enter Collaborator Issuing Details: ");
    }

    private String requestAddress() {
        return Utils.readLineFromConsole("Enter Collaborator Address: ");
    }

    private String requestPhoneNumber() {
        return Utils.readLineFromConsole("Enter Collaborator Phone Number: ");
    }

    private String requestEmail() {
        return Utils.readLineFromConsole("Enter Collaborator Email: ");
    }

    private String requestIdentificationDocument() {
        return Utils.readLineFromConsole("Enter Collaborator Identification Document: ");
    }
}