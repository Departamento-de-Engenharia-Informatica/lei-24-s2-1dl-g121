package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

/**
 * Controller for registering collaborators.
 * This controller handles the retrieval of collaborators, jobs and authentication from their respective repositories,
 * and the registration of a collaborator.
 */
public class RegisterCollaboratorController {

    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;
    private JobRepository jobRepository;

    /**
     * Default constructor that initializes the repositories from the singleton instance.
     */
    public RegisterCollaboratorController() {
        getCollaboratorRepository();
        getAuthenticationRepository();
        getJobRepository();
    }

    /**
     * Constructor that allows the injection of repositories.
     *
     * @param collaboratorRepository the collaborator repository to use
     * @param jobRepository the job repository to use
     */
    public RegisterCollaboratorController(CollaboratorRepository collaboratorRepository, JobRepository jobRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.jobRepository = jobRepository;
    }

    /**
     * Retrieves the job repository from the singleton instance if it's not already set.
     *
     * @return the job repository
     */
    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    /**
     * Retrieves the list of all jobs from the job repository.
     *
     * @return the list of all jobs
     */
    public List<Job> getJobList(){
        return jobRepository.getJobs();
    }

    /**
     * Retrieves a job from the job repository by its name.
     *
     * @param name the name of the job
     * @return the job with the given name, or null if no such job exists
     */
    public Job getJobByName(String name){
        if (jobRepository.getJobByName(name).isPresent()) {
            return jobRepository.getJobByName(name).get();
        } else {
            return null;
        }
    }

    /**
     * Retrieves the collaborator repository from the singleton instance if it's not already set.
     *
     * @return the collaborator repository
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Retrieves the authentication repository from the singleton instance if it's not already set.
     *
     * @return the authentication repository
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Registers a new collaborator in the collaborator repository.
     *
     * @param name the name of the collaborator
     * @param birthDetails the birth details of the collaborator
     * @param issuingDetails the issuing details of the collaborator
     * @param address the address of the collaborator
     * @param phoneNumber the phone number of the collaborator
     * @param email the email of the collaborator
     * @param identificationDocument the identification document of the collaborator
     * @param job the job of the collaborator
     * @return an optional containing the registered collaborator if the registration was successful, empty otherwise
     */
    public Optional<Collaborator> registerCollaborator(String name, String birthDetails, String issuingDetails, String address, String phoneNumber, String email, String taxPayerDocument, String identificationDocument, Job job) {
        Collaborator newCollaborator = new Collaborator(name,  birthDetails,  issuingDetails,  address,  phoneNumber,  email, taxPayerDocument, job);
        if (!collaboratorRepository.getCollaborators().contains(newCollaborator)) {
            try{
                collaboratorRepository.add(newCollaborator);
            } catch (UnsupportedOperationException e){
                System.out.println("Error:" + e.getMessage());
            }
            return Optional.of(newCollaborator);
        }
        return Optional.empty();
    }

    /**
     * Retrieves the employee from the current session.
     *
     * @return the employee from the current session
     */
    private Employee getEmployeeFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new Employee(email.getEmail());
    }
}