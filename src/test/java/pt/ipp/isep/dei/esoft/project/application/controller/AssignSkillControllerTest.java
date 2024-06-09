package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import static org.junit.jupiter.api.Assertions.*;

class AssignSkillControllerTest {
    private AssignSkillController controller;
    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;

    @BeforeEach
    void setUp() {
        collaboratorRepository = new CollaboratorRepository();
        skillRepository = new SkillRepository();
        controller = new AssignSkillController(collaboratorRepository, skillRepository);
    }

    @Test
    void assignSkillToExistingCollaborator() {
        Skill skill = new Skill("Test");
        skillRepository.add(skill);
        Collaborator collaborator = new Collaborator("Test", "Test","Test", "Test", "Test", "Test", "Test","12312312", new Job("Test"));
        collaboratorRepository.add(collaborator);
        controller.assignSkill("Test", "Test");
        assertTrue(collaborator.getSkillList().contains(skill));
    }

    @Test
    void assignNonExistingSkillToCollaborator() {
        Collaborator collaborator = new Collaborator("Test", "Test","Test", "Test", "Test", "Test", "Test","12312312", new Job("Test"));
        collaboratorRepository.add(collaborator);
        assertThrows(NullPointerException.class, () -> controller.assignSkill("Test", "NonExistingSkill"));
    }

    @Test
    void assignSkillToNonExistingCollaborator() {
        Skill skill = new Skill("Test");
        skillRepository.add(skill);
        assertThrows(NullPointerException.class, () -> controller.assignSkill("NonExistingCollaborator", "Test"));
    }

    @Test
    void verifyNonExistingCollaboratorById() {
        assertTrue(controller.verifyCollaboratorById("NonExistingCollaborator"));
    }

    @Test
    void verifyExistingCollaboratorById() {
        Collaborator collaborator = new Collaborator("Test", "Test","Test", "Test", "Test", "Test", "Test","12312312", new Job("Test"));
        collaboratorRepository.add(collaborator);
        assertFalse(controller.verifyCollaboratorById("Test"));
    }

    @Test
    void verifyNonExistingSkillByName() {
        assertTrue(controller.verifySkillByName("NonExistingSkill"));
    }

    @Test
    void verifyExistingSkillByName() {
        Skill skill = new Skill("Test");
        skillRepository.add(skill);
        assertFalse(controller.verifySkillByName("Test"));
    }
}