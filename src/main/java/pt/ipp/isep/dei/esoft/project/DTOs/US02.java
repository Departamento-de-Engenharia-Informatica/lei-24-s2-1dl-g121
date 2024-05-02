package pt.ipp.isep.dei.esoft.project.DTOs;

import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.Objects;
import java.util.Scanner;

public class US02 implements Runnable{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String job = Utils.readLineFromConsole("\"Hi! %nWhat's the name of the job you will register today?");
        int numberOfSkills = Utils.readIntegerFromConsole("How many skills are needed to this job?");
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

    @Override
    public void run() {

    }
}
