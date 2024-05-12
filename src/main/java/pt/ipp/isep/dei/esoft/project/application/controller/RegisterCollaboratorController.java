package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Optional;

public class RegisterCollaboratorController {

    private CollaboratorRepository collaboratorRepository;
    private AuthenticationRepository authenticationRepository;
    private JobRepository jobRepository;


    //Repository instances are obtained from the Repositories class
    public RegisterCollaboratorController() {
        getCollaboratorRepository();
        getAuthenticationRepository();
        getJobRepository();
    }


    //Allows receiving the repositories as parameters for testing purposes
    public RegisterCollaboratorController(CollaboratorRepository collaboratorRepository,
                                          AuthenticationRepository authenticationRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.authenticationRepository = authenticationRepository;
    }

    private JobRepository getJobRepository() {
        if (jobRepository == null) {
            Repositories repositories = Repositories.getInstance();
            jobRepository = repositories.getJobRepository();
        }
        return jobRepository;
    }

    public List<Job> getJobList(){
        return jobRepository.getJobs();
    }

    public Job getJobByName(String name){
        if (jobRepository.getJobByName(name).isPresent()) {
            return jobRepository.getJobByName(name).get();
        } else {
            return null;
        }
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();

            // Get the SkillRepository
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            // Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public Optional<Collaborator> registerCollaborator(String name, String birthDetails, String issuingDetails, String address, String phoneNumber, String email, String identificationDocument, Job job) {
        Collaborator newCollaborator = new Collaborator(name,  birthDetails,  issuingDetails,  address,  phoneNumber,  email,  identificationDocument, job);
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

    private Employee getEmployeeFromSession() {
        Email email = getAuthenticationRepository().getCurrentUserSession().getUserId();
        return new Employee(email.getEmail());
    }
}
