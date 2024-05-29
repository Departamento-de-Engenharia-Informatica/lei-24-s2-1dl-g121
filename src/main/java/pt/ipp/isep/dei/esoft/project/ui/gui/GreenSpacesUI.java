package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;

import java.net.URL;
import java.util.*;

public class GreenSpacesUI implements Initializable {

    @FXML
    public ChoiceBox greenSpaceType;
    @FXML
    public Label messageLbl;
    @FXML
    public TextField addressTxt;
    @FXML
    public TextField areaTxt;
    @FXML
    public TextField nameTxt;
    @FXML
    public Button submitBtn;

    private GreenSpacesController controller;
    private String type;
    private String address;
    private double area;
    private String name;
    String[] validTypes = {"garden", "medium-sized park", "large-sized park"};

    static Scanner sc = new Scanner(System.in);

    public GreenSpacesUI(){ controller = new GreenSpacesController();}
    private GreenSpacesController getController(){ return controller;}

//    public void run() {
//        System.out.println("\n\n--- New Green Space ------------------------");
//
//        requestData();
//        submitData();
//    }

    @FXML
    private void submitData() {
        //Optional<GreenSpaces> greenSpace = getController().createGreenSpace(type, area, address, name);

        messageLbl.setText("Green Space created successfully!!!");
    }

    @FXML
    private void requestData() {
        type = greenSpaceType.getValue().toString();
        address = addressTxt.getText();
        area = Double.parseDouble(areaTxt.getText());
        name = nameTxt.getText();

        generalValidation();
    }

    private void generalValidation() {
        boolean valid = true;

        if (Objects.equals(addressTxt.getText(), "")) {
            valid = false;
        } else if (Objects.equals(areaTxt.getText(), "")) {
            valid = false;
        }else if (Objects.equals(nameTxt.getText(), "")){
            valid = false;
        }

        if(valid){
            submitData();
        }
        else {
            messageLbl.setText("All the boxes should be filled!");
        }
    }

//    private String requestName() {
//        while(name == null){
//            name = Utils.readLineFromConsole("What's the name you want to give to the "+ type + " you added?");
//
//        }
//        return name;
//    }
//
//    private double requestArea() {
//        while(area <= 0) {
//            area = Utils.readDoubleFromConsole("What's the area of the "+type+"?");
//        }
//        return area;
//    }
//
//    private String requestAddress() {
//        while(address == null) {
//            System.out.println("What's the address?");
//            address = sc.nextLine();
//        }
//        return address;
//    }

//    private String requestType() {
//        System.out.println("What's the type of green space you want to add? (garden, medium-sized park, large-sized park) ");
////        validateType();
//        return type;
//    }

//    private void validateType() {
//        while (true) {
//            type = sc.nextLine().trim();
//
//            if (isValidType(type, validTypes)) {
//                break;
//            } else {
//                System.out.println("Invalid type. Enter the type again (garden, medium-sized park, large-sized park): ");
//            }
//        }
//    }
//
//
//    private static boolean isValidType(String type, String[] validTypes) {
//        for (String validName : validTypes) {
//            if (validName.equalsIgnoreCase(type)) {
//                return true;
//            }
//        }
//        return false;
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> stringList = new ArrayList<>();

        stringList.add("Garden");
        stringList.add("Medium-sized Park");
        stringList.add("Large-sized Park");

        greenSpaceType.getItems().addAll(stringList);
    }

}
