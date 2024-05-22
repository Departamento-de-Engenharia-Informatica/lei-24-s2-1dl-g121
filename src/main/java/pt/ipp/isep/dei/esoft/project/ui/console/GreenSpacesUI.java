package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class GreenSpacesUI implements Runnable {
    private GreenSpacesController controller;
    private String type;
    private String address;
    private double area;
    private String name;
    String[] validTypes = {"garden", "medium-sized park", "large-sized park"};

    static Scanner sc = new Scanner(System.in);

    public GreenSpacesUI(){ controller = new GreenSpacesController();}
    private GreenSpacesController getController(){ return controller;}

    public void run() {
        System.out.println("\n\n--- New Green Space ------------------------");

        requestData();
        submitData();
    }

    private void submitData() {
        Optional<GreenSpaces> greenSpace = getController().createGreenSpace(type, area, address, name);

        System.out.println("Green Space succefully created!!!");

    }

    private void requestData() {
        type = requestType();
        address = requestAddress();
        area = requestArea();
        name = requestName();
    }

    private String requestName() {
        while(name == null){
            System.out.printf("What's the name you want to give to the %s you added?%n", type);
            name = sc.nextLine();
        }
        return name;
    }

    private double requestArea() {
        while(area <= 0) {
            System.out.printf("What's the area of the %s?%n", type);
            area = sc.nextDouble();
        }
        return area;
    }

    private String requestAddress() {
        while(address == null) {
            System.out.println("What's the address?");
            address = sc.nextLine();
        }
        return address;
    }

    private String requestType() {
        System.out.println("What's the type of green space you want to add? (garden, medium-sized park, large-sized park) ");
        validateType();
        return type;
    }

    private void validateType() {
        while (true) {
            type = sc.nextLine().trim();

            if (isValidType(type, validTypes)) {
                break;
            } else {
                System.out.println("Invalid type. Enter the type again (garden, medium-sized park, large-sized park): ");
            }
        }
    }


    private static boolean isValidType(String type, String[] validTypes) {
        for (String validName : validTypes) {
            if (validName.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }
}
