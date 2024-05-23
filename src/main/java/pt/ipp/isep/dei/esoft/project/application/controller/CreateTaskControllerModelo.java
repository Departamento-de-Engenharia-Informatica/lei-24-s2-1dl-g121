package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.TaskModelo;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategoryModelo;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrganizationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.TaskCategoryRepository;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

public class CreateTaskControllerModelo {

    private OrganizationRepository organizationRepository;
    private TaskCategoryRepository taskCategoryRepository;
    private AuthenticationRepository authenticationRepository;


    //Repository instances are obtained from the Repositories class
    public CreateTaskControllerModelo() {
        getOrganizationRepository();
        getTaskCategoryRepository();
        getAuthenticationRepository();
    }

    //Allows receiving the repositories as parameters for testing purposes
    public CreateTaskControllerModelo(OrganizationRepository organizationRepository,
                                      TaskCategoryRepository taskCategoryRepository,
                                      AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.taskCategoryRepository = taskCategoryRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private TaskCategoryRepository getTaskCategoryRepository() {
        if (taskCategoryRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            taskCategoryRepository = repositories.getTaskCategoryRepository();
        }
        return taskCategoryRepository;
    }

    private OrganizationRepository getOrganizationRepository() {
        if (organizationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            organizationRepository = repositories.getOrganizationRepository();
        }
        return organizationRepository;

    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public Optional<TaskModelo> createTask(String reference, String description, String informalDescription,
                                           String technicalDescription, int duration, double cost,
                                           String taskCategoryDescription) {

        TaskCategoryModelo taskCategory = getTaskCategoryByDescription(taskCategoryDescription);

        Employee employee = getEmployeeFromSession();
        Optional<Organization> organization = getOrganizationRepository().getOrganizationByEmployee(employee);

        Optional<TaskModelo> newTask = Optional.empty();

        if (organization.isPresent()) {
            newTask = organization.get()
                    .createTask(reference, description, informalDescription, technicalDescription, duration, cost,
                            taskCategory, employee);
        }
        return newTask;
    }

    private Employee getEmployeeFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new Employee(email.getEmail());
    }

    private TaskCategoryModelo getTaskCategoryByDescription(String taskCategoryDescription) {
        TaskCategoryRepository taskCategoryRepository = getTaskCategoryRepository();

        //Get the TaskCategory by its description
        TaskCategoryModelo taskCategoryByDescription =
                getTaskCategoryRepository().getTaskCategoryByDescription(taskCategoryDescription);
        return taskCategoryByDescription;

    }

    //return the list of task categories
    public List<TaskCategoryModelo> getTaskCategories() {
        TaskCategoryRepository taskCategoryRepository = getTaskCategoryRepository();
        return taskCategoryRepository.getTaskCategories();
    }
}