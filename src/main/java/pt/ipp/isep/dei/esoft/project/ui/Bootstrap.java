package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpacesController;
import pt.ipp.isep.dei.esoft.project.application.controller.ToDoListController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.List;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addSkills();
        addJobs();
        addCollaborators();
        addOrganization();
        addGreenSpaces();
        addTasks();
        addEntries();
        addUsers();
    }

    private void addEntries() {
//        ToDoListController controller = new ToDoListController();
//        Agenda agenda = Repositories.getInstance().getAgenda();
//        agenda.add(new Entry("Entry 1", controller.getTaskByReference("Task 1"), new Date(2024,6,3), status.PLANNED));
//        agenda.add(new Entry("Entry 2", controller.getTaskByReference("Task 2"), new Date(2024,5,1), status.CANCELED));

        try {
            FileInputStream fileIn = new FileInputStream("saveFiles/agenda.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Repositories.getInstance().setAgenda((Agenda) in.readObject());
            in.close();
            fileIn.close();
        } catch (FileNotFoundException f) {
            // File does not exist yet, do nothing
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Agenda class not found");
            c.printStackTrace();
        }
    }

    private void addTasks() {
//        GreenSpacesController controller = new GreenSpacesController();
//        ToDoList toDoList = Repositories.getInstance().getToDoList();
//        toDoList.add(new Task("Task 1", "Description 1", 1, urgencyDegree.LOW, controller.getGreenSpaceByName("ArcaAgua")));
//        toDoList.add(new Task("Task 2", "Description 2", 1, urgencyDegree.MEDIUM, controller.getGreenSpaceByName("ArcaAgua")));
//        toDoList.add(new Task("Task 3", "Description 3", 1, urgencyDegree.HIGH, controller.getGreenSpaceByName("ArcaAgua")));

            try {
                FileInputStream fileIn = new FileInputStream("saveFiles/toDoList.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Repositories.getInstance().setToDoList((ToDoList) in.readObject());
                in.close();
                fileIn.close();
            } catch (FileNotFoundException f) {
                // File does not exist yet, do nothing
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("ToDoList class not found");
                c.printStackTrace();
            }
    }

    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");
        organization.addEmployee(new Employee("admin@this.app"));
        organization.addEmployee(new Employee("employee@this.app"));
        organizationRepository.add(organization);

        try {
            FileInputStream fileIn = new FileInputStream("saveFiles/organizationRepository.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Repositories.getInstance().setOrganizationRepository((OrganizationRepository) in.readObject());
            in.close();
            fileIn.close();
        } catch (FileNotFoundException f) {
            // File does not exist yet, do nothing
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("OrganizationRepository class not found");
            c.printStackTrace();
        }
    }

    private void addSkills() {
        //TODO: add bootstrap Skills here

        // Get skill repository
        SkillRepository skillRepository = Repositories.getInstance().getSkillRepository();

        // Add skills to the repository
        skillRepository.add(new Skill("Communication"));
        skillRepository.add(new Skill("Problem Solving"));
        skillRepository.add(new Skill("Teamwork"));
        skillRepository.add(new Skill("Leadership"));
        skillRepository.add(new Skill("Time Management"));

        try {
            FileInputStream fileIn = new FileInputStream("saveFiles/skillRepository.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Repositories.getInstance().setSkillRepository((SkillRepository) in.readObject());
            in.close();
            fileIn.close();
        } catch (FileNotFoundException f) {
            // File does not exist yet, do nothing
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("SkillRepository class not found");
            c.printStackTrace();
        }
    }

    private void addJobs() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        jobRepository.addJob(new Job("SoftwareDeveloper"));
        jobRepository.addJob(new Job("SoftwareTester"));
        jobRepository.addJob(new Job("ProjectManager"));
        jobRepository.addJob(new Job("BusinessAnalyst"));

        try {
            FileInputStream fileIn = new FileInputStream("saveFiles/jobRepository.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Repositories.getInstance().setJobRepository((JobRepository) in.readObject());
            in.close();
            fileIn.close();
        } catch (FileNotFoundException f) {
            // File does not exist yet, do nothing
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("JobRepository class not found");
            c.printStackTrace();
        }
    }

    private void addCollaborators() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        collaboratorRepository.add(new Collaborator("Jorge", "2019-01-01", "ns", "alias", "email", "phone", "10012", "001", new Job("SoftwareDeveloper")));
        collaboratorRepository.add(new Collaborator("Joao", "2019-01-01", "ns", "alias", "email", "phone", "13", "002", new Job("SoftwareDeveloper")));
        collaboratorRepository.add(new Collaborator("Tiago", "2019-01-01", "ns", "alias", "email", "phone", "14", "003", new Job("SoftwareDeveloper")));

        try {
            FileInputStream fileIn = new FileInputStream("saveFiles/collaboratorRepository.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Repositories.getInstance().setCollaboratorRepository((CollaboratorRepository) in.readObject());
            in.close();
            fileIn.close();
        } catch (FileNotFoundException f) {
            // File does not exist yet, do nothing
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("CollaboratorRepository class not found");
            c.printStackTrace();
        }
    }



    private void addGreenSpaces() {
        GreenSpacesRepository greenSpacesRepository = Repositories.getInstance().getGreenSpacesRepository();
        greenSpacesRepository.add(new GreenSpaces("garden", 100, "Praça de 9 de Abril 121, 4200-422 Porto", "ArcaAgua", "exemplo@exemplo.com"));
        greenSpacesRepository.add(new GreenSpaces("garden", 200, "Praça de 10 de Abril 122, 4200-423 Porto", "Parque da Cidade", "exemplo@exemplo.com"));
        greenSpacesRepository.add(new GreenSpaces("garden", 150, "Praça de 11 de Abril 123, 4200-424 Porto", "Covelo", "outroexemplo@exemplo.com"));

        try {
            FileInputStream fileIn = new FileInputStream("saveFiles/greenSpaces.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Repositories.getInstance().setGreenSpacesRepository((GreenSpacesRepository) in.readObject());
            in.close();
            fileIn.close();
        } catch (FileNotFoundException f) {
            // File does not exist yet, do nothing
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("GreenSpacesRepository class not found");
            c.printStackTrace();
        }
    }

    private void addUsers() {
            //TODO: add Authentication users here: should be created for each user in the organization
            AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
            authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
            authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
            authenticationRepository.addUserRole(AuthenticationController.ROLE_GSU, AuthenticationController.ROLE_GSU);
            authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
            authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
            authenticationRepository.addUserRole(AuthenticationController.ROLE_QAM, AuthenticationController.ROLE_QAM);
            authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLAB, AuthenticationController.ROLE_COLLAB);

            authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                    AuthenticationController.ROLE_ADMIN);
            authenticationRepository.addUserWithRole("gsm", "gsm@this.app", "gsm",
                AuthenticationController.ROLE_GSM);
            authenticationRepository.addUserWithRole("qam", "qam@this.app", "qam",
                AuthenticationController.ROLE_QAM);
            authenticationRepository.addUserWithRole("gsu", "gsu@this.app", "gsu",
                AuthenticationController.ROLE_GSU);
            authenticationRepository.addUserWithRole("collab", "collab@this.app", "collab",
                AuthenticationController.ROLE_COLLAB);
            authenticationRepository.addUserWithRole("HRM", "hrm@this.app", "hrm",
                    AuthenticationController.ROLE_HRM);
            authenticationRepository.addUserWithRole("VFM", "vfm@this.app", "vfm",
                    AuthenticationController.ROLE_VFM);

        try {
            FileInputStream fileIn = new FileInputStream("saveFiles/authenticationRepository.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Repositories.getInstance().setAuthenticationRepository((AuthenticationRepository) in.readObject());
            in.close();
            fileIn.close();
        } catch (FileNotFoundException f) {
            // File does not exist yet, do nothing
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("AuthenticationRepository class not found");
            c.printStackTrace();
        }
    }
}