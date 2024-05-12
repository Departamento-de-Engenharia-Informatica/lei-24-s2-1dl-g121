package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SkillRepositoryTest {

    @Test
    void ensureNewSkillSuccessfullyAdded() {
        SkillRepository skillRepository = new SkillRepository();
        Skill skill = new Skill("Programming");
        Optional<Skill> addedSkill = skillRepository.add(skill);
        assertTrue(addedSkill.isPresent());
        assertEquals(skill, addedSkill.get());
    }

    @Test
    void ensureAddingDuplicateSkillFails() {
        SkillRepository skillRepository = new SkillRepository();
        Skill skill = new Skill("Programming");
        skillRepository.add(skill);
        Optional<Skill> duplicateSkill = skillRepository.add(skill);
        assertTrue(duplicateSkill.isEmpty());
    }

    @Test
    void ensureAddingDifferentSkillsWorks() {
        SkillRepository skillRepository = new SkillRepository();
        Skill skillOne = new Skill("Programming");
        Skill skillTwo = new Skill("Database Management");
        skillRepository.add(skillOne);
        Optional<Skill> addedSkill = skillRepository.add(skillTwo);
        assertTrue(addedSkill.isPresent());
        assertEquals(skillTwo, addedSkill.get());
    }

    @Test
    void ensureGetSkillsReturnsAnImmutableList() {
        SkillRepository skillRepository = new SkillRepository();
        Skill skill = new Skill("Programming");
        skillRepository.add(skill);
        assertThrows(UnsupportedOperationException.class,
                () -> skillRepository.getSkills().add(new Skill("Database Management")));
    }

    @Test
    void ensureGetSkillsReturnsTheCorrectList() {
        SkillRepository skillRepository = new SkillRepository();
        Skill skill = new Skill("Programming");
        skillRepository.add(skill);
        assertEquals(1, skillRepository.getSkills().size());
        assertEquals(skill, skillRepository.getSkills().get(0));
    }
}
