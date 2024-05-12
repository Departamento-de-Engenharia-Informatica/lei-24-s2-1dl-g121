package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addSkills();
        addJobs();
        addOrganization();
        addUsers();
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

    private void addTaskCategories() {
        //TODO: add bootstrap Task Categories here

        //get task category repository
        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
        taskCategoryRepository.add(new TaskCategory("Analysis"));
        taskCategoryRepository.add(new TaskCategory("Design"));
        taskCategoryRepository.add(new TaskCategory("Implementation"));
        taskCategoryRepository.add(new TaskCategory("Development"));
        taskCategoryRepository.add(new TaskCategory("Testing"));
        taskCategoryRepository.add(new TaskCategory("Deployment"));
        taskCategoryRepository.add(new TaskCategory("Maintenance"));
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

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSU, AuthenticationController.ROLE_GSU);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_QAM, AuthenticationController.ROLE_QAM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLAB, AuthenticationController.ROLE_COLLAB);

        authenticationRepository.addUserWithRole("Main Administrator", "admin@this.app", "admin",
                AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserWithRole("Jorge", "jorge.pais@musgo.sublime", "jorginho123",
                AuthenticationController.ROLE_HRM);
    }
}