package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import static org.junit.jupiter.api.Assertions.*;

class RegisterCollaboratorControllerTest {

    private RegisterCollaboratorController controller;
    private CollaboratorRepository collaboratorRepository;
    private JobRepository jobRepository;

    @BeforeEach
    void setUp() {
        collaboratorRepository = new CollaboratorRepository();
        jobRepository = new JobRepository();
        controller = new RegisterCollaboratorController(collaboratorRepository, jobRepository);
    }

    @Test
    void testGetJobList() {
        Job job = new Job("Test");
        jobRepository.addJob(job);
        assertTrue(controller.getJobList().contains(job));
    }

    @Test
    void ensureGetJobByNameWorks() {
        Job job = new Job("Test");
        jobRepository.addJob(job);
        assertEquals(job, controller.getJobByName("Test"));
    }

    @Test
    void ensureGetJobByNameFails(){
        assertNull(controller.getJobByName("Test"));
    }
}