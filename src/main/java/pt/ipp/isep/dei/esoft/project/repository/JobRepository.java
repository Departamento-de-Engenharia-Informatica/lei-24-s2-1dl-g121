package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRepository {
    private final List<Job> jobs;

    public JobRepository() {
        jobs = new ArrayList<>();
    }

    public Optional<Job> addJob(Job job) {
        Optional<Job> newJob = Optional.empty();
        if (validateJob(job)) {
            newJob = Optional.of(job.clone());
            jobs.add(newJob.get());
        }
        return newJob;
    }

    private boolean validateJob(Job job) {
        boolean isValid = !jobs.contains(job);
        return isValid;
    }

    public List<Job> getJobs() {
        return List.copyOf(jobs);
    }

}
