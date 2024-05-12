package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {

    @Test
    void ensureSkillIsCreatedSuccessfully() {
        Skill skill = new Skill("Programming");
        assertNotNull(skill);
    }

    @Test
    void ensureSkillNameIsNotNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Skill(null));
    }

    @Test
    void ensureSkillNameIsNotEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Skill(""));
    }

    @Test
    void ensureSkillNameContainsOnlyLettersAndSpaces() {
        assertThrows(IllegalArgumentException.class,
                () -> new Skill("123"));
    }

    @Test
    void ensureSkillNameContainsOnlyLettersAndSpaces_WithSpaces() {
        Skill skill = new Skill("Software Development");
        assertNotNull(skill);
    }

    @Test
    void testEqualsSameObject() {
        Skill skill = new Skill("Programming");
        assertEquals(skill, skill);
    }

    @Test
    void testEqualsDifferentClass() {
        Skill skill = new Skill("Programming");
        assertNotEquals(skill, new Object());
    }

    @Test
    void testEqualsNull() {
        Skill skill = new Skill("Programming");
        assertNotEquals(skill, null);
    }

    @Test
    void testEqualsDifferentObject() {
        Skill skill = new Skill("Programming");
        Skill skill1 = new Skill("Database Management");
        assertNotEquals(skill, skill1);
    }

    @Test
    void testEqualsSameObjectSameName() {
        Skill skill = new Skill("Programming");
        Skill skill1 = new Skill("Programming");
        assertEquals(skill, skill1);
    }

    @Test
    void testHashCodeSameObject() {
        Skill skill = new Skill("Programming");
        assertEquals(skill.hashCode(), skill.hashCode());
    }

    @Test
    void testHashCodeDifferentObject() {
        Skill skill = new Skill("Programming");
        Skill skill1 = new Skill("Database Management");
        assertNotEquals(skill.hashCode(), skill1.hashCode());
    }

    @Test
    void ensureCloneWorks() {
        Skill skill = new Skill("Programming");
        Skill clone = skill.clone();
        assertEquals(skill, clone);
    }
}
