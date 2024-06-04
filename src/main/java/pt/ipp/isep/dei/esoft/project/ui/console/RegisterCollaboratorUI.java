package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterCollaboratorController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

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

        //TODO: Add validation for each field
        String name = requestName();
        String birthDetails = requestBirthDetails();
        String issuingDetails = requestIssuingDetails();
        String address = requestAddress();
        String phoneNumber = requestPhoneNumber();
        String email = requestEmail();
        String taxPayerDcmnt = requestTaxPayerDocument();
        String identificationDocument = requestIdentificationDocument();

        Optional<Collaborator> optCollaborator = controller.registerCollaborator(name, birthDetails, issuingDetails, address, phoneNumber, email, taxPayerDcmnt, identificationDocument, job);
        if (optCollaborator.isPresent()) {
            System.out.println("Collaborator registered successfully!");
        } else {
            System.out.println("Collaborator not registered!");
        }
    }

    private String requestTaxPayerDocument() {
        String taxPayerDocument;
        boolean isValid;
        do {
            taxPayerDocument = Utils.readLineFromConsole("Enter Collaborator Taxpayer Document: ");
            isValid = validateTaxPayerDocument(taxPayerDocument);
            if (!isValid) {
                System.out.println("Invalid taxpayer document. Please enter an 9-digit number without letters or special characters.");
            }
        } while (!isValid);
        return taxPayerDocument;
    }

    private boolean validateTaxPayerDocument(String taxPayerDocument) {
        String regex = "^\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taxPayerDocument);
        return matcher.matches();
    }



    public void presentJobList(){
        System.out.println("Jobs:");
        List<Job> jobList = controller.getJobList();
        for (Job job : jobList){
            System.out.println(job.getName());
        }
    }

    private String requestName() {
        String name;
        while (true) {
            name = Utils.readLineFromConsole("Enter Collaborator Name: ");
            if (isValidName(name)) {
                break;
            } else {
                System.out.println("Invalid name. Please enter only alphabetic characters and spaces.");
            }
        }
        return name;
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z\\s]+");
    }


    private String requestBirthDetails() {
        String birthDetails;
        while (true) {
            birthDetails = Utils.readLineFromConsole("Enter Collaborator Birth Details (dd/mm/yyyy): ");
            if (isValidBirthDetails(birthDetails)) {
                break;
            } else {
                System.out.println("Invalid date. Please enter the date in the format dd/mm/yyyy.");
            }
        }
        return birthDetails;
    }

    private boolean isValidBirthDetails(String birthDetails) {
        // Regex to match the date format dd/mm/yyyy
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(birthDetails);
        if (!matcher.matches()) {
            return false;
        }

        // Split the date components
        String[] parts = birthDetails.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // Check month validity
        if (month < 1 || month > 12) {
            return false;
        }

        // Check day validity based on the month and leap year
        if (day < 1) {
            return false;
        }

        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int[] daysInMonth = {31, (isLeapYear ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        return day <= daysInMonth[month - 1];
    }


    private String requestIssuingDetails() {
        String issuingDetails;
        while (true) {
            issuingDetails = Utils.readLineFromConsole("Enter Collaborator admission date (dd/mm/yyyy): ");
            if (isValidBirthDetails(issuingDetails)) {
                break;
            } else {
                System.out.println("Invalid date. Please enter the date in the format dd/mm/yyyy.");
            }
        }
        return issuingDetails;
    }

    private boolean isValidIssuingDetails(String issuingDetails) {
            // Regex to match the date format dd/mm/yyyy
            String regex = "^\\d{2}/\\d{2}/\\d{4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(issuingDetails);
            if (!matcher.matches()) {
                return false;
            }

            // Split the date components
            String[] parts = issuingDetails.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            // Check month validity
            if (month < 1 || month > 12) {
                return false;
            }

            // Check day validity based on the month and leap year
            if (day < 1) {
                return false;
            }

            if (year > 20224){
                return false;
            }

            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            int[] daysInMonth = {31, (isLeapYear ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            return day <= daysInMonth[month - 1];
        }
    private String requestAddress() {
        String address;
        while (true) {
            address = Utils.readLineFromConsole("Enter Collaborator Address (format: street numberOfhouse): ");
            if (isValidAddress(address)) {
                break;
            } else {
                System.out.println("Invalid address. Please enter in the format: street numberOfhouse, without special characters.");
            }
        }
        return address;
    }

    private boolean isValidAddress(String address) {
        return address.matches("[a-zA-Z\\s]+\\s\\d+");
    }
    private String requestPhoneNumber() {
        String phoneNumber;
        boolean isValid;
        do {
            phoneNumber = Utils.readLineFromConsole("Enter Collaborator Phone Number: ");
            isValid = validatePhoneNumber(phoneNumber);
            if (!isValid) {
                System.out.println("Invalid phone number. Please enter a 9-digit number starting with 91, 92, 93, or 96.");
            }
        } while (!isValid);
        return phoneNumber;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        // Regex to match a 9-digit phone number starting with 91, 92, 93, or 96
        String regex = "^(91|92|93|96)\\d{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    private String requestEmail() {
        String email;
        boolean isValid;
        do {
            email = Utils.readLineFromConsole("Enter Collaborator Email: ");
            isValid = validateEmail(email);
            if (!isValid) {
                System.out.println("Invalid email. Please enter an email in the format letters@letters.letters");
            }
        } while (!isValid);
        return email;
    }

    private boolean validateEmail(String email) {
        String regex = "^[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private String requestIdentificationDocument() {
        String idDocument;
        boolean isValid;
        do {
            idDocument = Utils.readLineFromConsole("Enter Collaborator Identification Document: ");
            isValid = validateIdentificationDocument(idDocument);
            if (!isValid) {
                System.out.println("Invalid identification document. Please enter an 8-digit number without letters or special characters.");
            }
        } while (!isValid);
        return idDocument;
    }

    private boolean validateIdentificationDocument(String idDocument) {
        String regex = "^\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(idDocument);
        return matcher.matches();
    }
}