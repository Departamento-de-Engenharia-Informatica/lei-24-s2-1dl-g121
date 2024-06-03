package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.GreenSpacesController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addSkills();
        addJobs();
        addCollaborators();
        addOrganization();
        addGreenSpaces();
        addTasks();
        addUsers();
    }

    private void addTasks() {
        GreenSpacesController controller = new GreenSpacesController();
        ToDoList toDoList = Repositories.getInstance().getToDoList();
        toDoList.add(new Task("Task 1", "Description 1", 1, urgencyDegree.LOW, controller.getGreenSpaceByName("ArcaAgua")));
        toDoList.add(new Task("Task 2", "Description 2", 1, urgencyDegree.MEDIUM, controller.getGreenSpaceByName("ArcaAgua")));
        toDoList.add(new Task("Task 3", "Description 3", 1, urgencyDegree.HIGH, controller.getGreenSpaceByName("ArcaAgua")));
    }

    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("This Company");
        organization.addEmployee(new Employee("admin@this.app"));
        organization.addEmployee(new Employee("employee@this.app"));
        organizationRepository.add(organization);
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
    }

    private void addJobs() {
        JobRepository jobRepository = Repositories.getInstance().getJobRepository();
        jobRepository.addJob(new Job("SoftwareDeveloper"));
        jobRepository.addJob(new Job("SoftwareTester"));
        jobRepository.addJob(new Job("ProjectManager"));
        jobRepository.addJob(new Job("BusinessAnalyst"));
    }

    private void addCollaborators() {
        CollaboratorRepository collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        collaboratorRepository.add(new Collaborator("Jorge", "2019-01-01", "ns", "alias", "email", "phone", "10012", new Job("SoftwareDeveloper")));
        collaboratorRepository.add(new Collaborator("Joao", "2019-01-01", "ns", "alias", "email", "phone", "13", new Job("SoftwareDeveloper")));
        collaboratorRepository.add(new Collaborator("Tiago", "2019-01-01", "ns", "alias", "email", "phone", "14", new Job("SoftwareDeveloper")));
    }



    private void addGreenSpaces() {
        GreenSpacesRepository greenSpacesRepository = Repositories.getInstance().getGreenSpacesRepository();
        greenSpacesRepository.add(new GreenSpaces("garden", 100, "Praça de 9 de Abril 121, 4200-422 Porto", "ArcaAgua", "exemplo@exemplo.com"));
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
            authenticationRepository.addUserWithRole("Jorge", "jorge.pais@musgo.sublime", "jorginho123",
                    AuthenticationController.ROLE_HRM);
            authenticationRepository.addUserWithRole("João", "jonny@musgo.sublime", "joao123",
                    AuthenticationController.ROLE_VFM);
    }
}