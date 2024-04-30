package pt.ipp.isep.dei.esoft.project.DTOs;

import java.util.Objects;
import java.util.Scanner;

public class US02 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.printf("Hi! %nWhat's the name of the job you will register today?%n");
        String job = sc.nextLine();
        System.out.printf("How many skills are needed to this job?%n");
        int numberOfSkills = sc.nextInt();
        String[] skills = new String[numberOfSkills];
        skillsNeeded(skills);
    }


    //baseado nas skills, ir buscar a outras US, essas skills associadas a trabalhadores para sugerir trabalhadores para este trabalho

    private static void skillsNeeded(String[] skills) {
        for (int i = 0; i < skills.length; i++) {
            System.out.println("Type the skill:");
            skills[i] = sc.next();
        }
    }
}
