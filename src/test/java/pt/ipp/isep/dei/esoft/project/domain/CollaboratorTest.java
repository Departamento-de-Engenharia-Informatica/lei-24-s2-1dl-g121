package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    Collaborator collaborator;
    private Job job;
    private Skill skill;

    @BeforeEach
    void setUp() {
        job = new Job("Test");
        skill = new Skill("Test");

        collaborator = new Collaborator("test", "Test", "test", "test", "test", "test", "test","12312312", job);
    }

    @Test
    void ensureAddSkillWorks() {
        assertTrue(collaborator.addSkill(skill));
    }

    @Test
    void ensureAddDuplicateSkillFails() {
        assertTrue(collaborator.addSkill(skill));
        assertFalse(collaborator.addSkill(skill));
    }

    @Test
    void isPartOfTeam() {

    }

    @Test
    void setTeam() {
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(collaborator, collaborator);
    }

    @Test
    void testEqualsDifferentClass() {
        assertNotEquals("", collaborator);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(null, collaborator);
    }

    @Test
    void testEqualsDifferentObject() {
        Collaborator collaborator1 = new Collaborator("test", "test", "test", "test", "test", "test","test","12312312", job);
        assertEquals(collaborator, collaborator1);
    }

    @Test
    void ensureCloneWorks() {
        Collaborator collaborator1 = collaborator.clone();
        assertEquals(collaborator, collaborator1);
    }
}