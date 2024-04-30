package pt.ipp.isep.dei.esoft.project.DTOs;

import java.util.Scanner;

public class US003 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter collaborator's name:");
        String name = scanner.nextLine();

        System.out.println("Enter collaborator's birth details:");
        String birthDetails = scanner.nextLine();

        System.out.println("Enter collaborator's issuing details:");
        String issuingDetails = scanner.nextLine();

        System.out.println("Enter collaborator's address:");
        String address = scanner.nextLine();

        System.out.println("Enter collaborator's phone number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter collaborator's email:");
        String email = scanner.nextLine();

        System.out.println("Enter collaborator's ID:");
        String ID = scanner.nextLine();

        Collaborator newCollaborator = new Collaborator(name, birthDetails, issuingDetails, address, phoneNumber, email, ID);

        System.out.println("\nNew collaborator registered:");
        System.out.println("Name: " + newCollaborator.getName());
        System.out.println("Birth Details: " + newCollaborator.getBirthDetails());
        System.out.println("Issuing Details: " + newCollaborator.getIssuingDetails());
        System.out.println("Address: " + newCollaborator.getAddress());
        System.out.println("Phone Number: " + newCollaborator.getPhoneNumber());
        System.out.println("Email: " + newCollaborator.getEmail());
        System.out.println("ID: " + newCollaborator.getID());
    }
}
