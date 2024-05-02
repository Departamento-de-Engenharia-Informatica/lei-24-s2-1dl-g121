package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Optional;
import java.util.Scanner;

/**
 * Register Collaborator UI (console).
 */
public class RegisterCollaboratorUI implements Runnable {

    private final RegisterCollaboratorController controller;
    private String name;
    private String birthDetails;
    private String issuingDetails;
    private String address;
    private String phoneNumber;
    private String email;
    private String identificationDocument;

    public RegisterCollaboratorUI() {
        controller = new RegisterCollaboratorController();
    }

    private RegisterCollaboratorController getController() {
        return controller;
    }

    public void run() {
        System.out.println("\n\n--- Register Collaborator ------------------------");

        requestData();

        submitData();
    }

    private void submitData() {
        Optional<Collaborator> collaborator = getController().registerCollaborator(name, birthDetails, issuingDetails, address, phoneNumber, email, identificationDocument);

        if (collaborator.isPresent()) {
            System.out.println("\nCollaborator successfully registered!");
        } else {
            System.out.println("\nCollaborator not registered!");
        }
    }

    private void requestData() {
        name = requestName();
        birthDetails = requestBirthDetails();
        issuingDetails = requestIssuingDetails();
        address = requestAddress();
        phoneNumber = requestPhoneNumber();
        email = requestEmail();
        identificationDocument = requestIdentificationDocument();
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