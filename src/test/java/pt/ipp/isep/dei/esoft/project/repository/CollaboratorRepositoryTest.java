package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorRepositoryTest {
    private CollaboratorRepository collaboratorRepository;

    @BeforeEach
    void setUp() {
        collaboratorRepository = new CollaboratorRepository();
    }

    @Test
    void addValidCollaborator() {
        Collaborator collaborator = new Collaborator("Test", "Test","Test", "Test", "Test", "Test", "Test","12312312", new Job("Test"));
        Optional<Collaborator> addedCollaborator = collaboratorRepository.add(collaborator);
        assertTrue(addedCollaborator.isPresent());
        assertEquals(collaborator, addedCollaborator.get());
    }

//    @Test
//    void addDuplicateCollaborator() {
//        Collaborator collaborator = new Collaborator("Test", "Test","Test", "Test", "Test", "Test", "Test","12312312", new Job("Test"));
//        collaboratorRepository.add(collaborator);
//        Optional<Collaborator> addedCollaborator = collaboratorRepository.add(collaborator);
//        assertFalse(addedCollaborator.isPresent());
//    }

    @Test
    void getExistingCollaboratorById() {
        Collaborator collaborator = new Collaborator("Test", "Test","Test", "Test", "Test", "Test", "Test","12312312", new Job("Test"));
        collaboratorRepository.add(collaborator);
        Collaborator retrievedCollaborator = collaboratorRepository.getCollaboratorById("12312312");
        assertEquals(collaborator, retrievedCollaborator);
    }

    @Test
    void getNonExistingCollaboratorById() {
        Collaborator retrievedCollaborator = collaboratorRepository.getCollaboratorById("NonExistingId");
        assertNull(retrievedCollaborator);
    }
}