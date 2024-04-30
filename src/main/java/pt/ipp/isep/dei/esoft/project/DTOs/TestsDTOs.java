package pt.ipp.isep.dei.esoft.project.DTOs;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.Scanner;

public class TestsDTOs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose the us you want to test: ");
        System.out.println("(4) - US0004 As an HRM, I want to assign one or more skills to a collaborator.");

        int option = in.nextInt();

        switch (option){
            case 4:
                runUS4();
                break;
        }
    }

    private static void runUS4() {
        Collaborator João = new Collaborator("João","01/01/1998","a","Rua Santo Antonio","9192912","joaotropinha@gmail.com","1255");
        Skill ConduzPesados = new Skill("Tem habilitação para conduzir pesados.");


    }
}
