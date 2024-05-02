package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TaskCategoryRepository;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        //addTaskCategories();
        addOrganization();
        addUsers();
    }

    private void addOrganization() {
        //TODO: add organizations bootstrap here
        //get organization repository
        OrganizationRepository organizationRepository = Repositories.getInstance().getOrganizationRepository();
        Organization organization = new Organization("MusgoSublime");
        organization.addEmployee(new Employee("12310912isep.ipp.pt"));
        organization.addEmployee(new Employee("employee@musgo.sublime"));
        organizationRepository.add(organization);
    }

//    private void addTaskCategories() {
//        //TODO: add bootstrap Task Categories here
//
//        //get task category repository
//        TaskCategoryRepository taskCategoryRepository = Repositories.getInstance().getTaskCategoryRepository();
//        taskCategoryRepository.add(new TaskCategory("Analysis"));
//        taskCategoryRepository.add(new TaskCategory("Design"));
//        taskCategoryRepository.add(new TaskCategory("Implementation"));
//        taskCategoryRepository.add(new TaskCategory("Development"));
//        taskCategoryRepository.add(new TaskCategory("Testing"));
//        taskCategoryRepository.add(new TaskCategory("Deployment"));
//        taskCategoryRepository.add(new TaskCategory("Maintenance"));
//    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_ADMIN, AuthenticationController.ROLE_ADMIN);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_VFM, AuthenticationController.ROLE_VFM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSM, AuthenticationController.ROLE_GSM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_GSU, AuthenticationController.ROLE_GSU);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_HRM, AuthenticationController.ROLE_HRM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_QAM, AuthenticationController.ROLE_QAM);
        authenticationRepository.addUserRole(AuthenticationController.ROLE_COLLAB, AuthenticationController.ROLE_COLLAB);

        authenticationRepository.addUserWithRole("Admin", "admin@musgo.sublime", "admin",
                AuthenticationController.ROLE_ADMIN);

        authenticationRepository.addUserWithRole("Jorge", "jorge.pais@musgo.sublime", "jorginho123",
                AuthenticationController.ROLE_HRM);
    }
}