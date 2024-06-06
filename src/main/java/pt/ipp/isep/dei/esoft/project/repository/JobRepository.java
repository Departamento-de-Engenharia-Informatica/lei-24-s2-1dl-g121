package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JobRepository implements Serializable {
    private final List<Job> jobs;

    public JobRepository() {
        jobs = new ArrayList<>();
    }

    public void addJob(Job job) {
        if (validateJob(job)) {
            jobs.add(job);
        } else {
            throw new UnsupportedOperationException("Job already exists");
        }
    }

    public Optional<Job> getJobByName(String name) {
        Optional<Job> returnJob = Optional.empty();

        for (Job job : jobs) {
            if (job.getName().equals(name)) {
                returnJob = Optional.of(job);
            }
        }

        return returnJob;
    }

    private boolean validateJob(Job job) {
        boolean isValid = !jobs.contains(job);
        return isValid;
    }

    public List<Job> getJobs() {
        return List.copyOf(jobs);
    }

}
