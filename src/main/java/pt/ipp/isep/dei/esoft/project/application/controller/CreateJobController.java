package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateJobController {

    private JobRepository jobRepository;
    private AuthenticationRepository authenticationRepository;

    // Repository instances are obtained from the Repositories class
    public CreateJobController() {
        Repositories repositories = Repositories.getInstance();
        jobRepository = repositories.getJobRepository();
        authenticationRepository = repositories.getAuthenticationRepository();
    }

    // Allows receiving the repositories as parameters for testing purposes
    public CreateJobController(JobRepository jobRepository, AuthenticationRepository authenticationRepository) {
        this.jobRepository = jobRepository;
        this.authenticationRepository = authenticationRepository;
    }

    public Optional<Job> createJob(String name) {
        Job newJob = new Job(name);
        if (!jobRepository.getJobs().contains(newJob)) {
            try {
                jobRepository.addJob(newJob);
                return Optional.of(newJob);
            } catch (UnsupportedOperationException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
        return Optional.empty();
    }
}
