package pt.ipp.isep.dei.esoft.project.DTOs;

import java.util.Scanner;

public class US01 {
    static Scanner ler = new Scanner (System.in);
    public static void main(String[] args) {
        System.out.printf("How many skills are going to be registered?%n");
        int numberOfSkills = ler.nextInt();
       String[] skills = new String[numberOfSkills];
        for (int i = 0; i < numberOfSkills; i++) {
            System.out.printf("Write the name of the skill.%n");
            skills[i]= ler.next();
        }
    }
}
