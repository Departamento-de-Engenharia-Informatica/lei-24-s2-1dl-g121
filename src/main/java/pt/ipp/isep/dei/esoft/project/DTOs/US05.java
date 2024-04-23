package pt.ipp.isep.dei.esoft.project.DTOs;

import java.util.Scanner;

public class US05 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.printf("Hi!%nWhat's the max size of the team you want to create?%n");
        int maxSize = sc.nextInt();
        int[] size = new int[maxSize];
        System.out.printf("How many skills integrate the set of skills needed to be a part of this team?%n");
        int numberOfSkills = sc.nextInt();
        String[] teamSkills = new String[numberOfSkills];
        teamSkillsFilling(teamSkills);
    }

    private static void teamSkillsFilling(String[] teamSkills) {
        for (int i = 0; i < teamSkills.length; i++) {
            System.out.println("Type skill needed:");
            teamSkills[i] = sc.next();
        }
    }
}
