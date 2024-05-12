package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        int numberOfSkills = 3;
        ArrayList<Skill> setOfSkills = new ArrayList<>();
        setOfSkills.add(new Skill("Java"));
        setOfSkills.add(new Skill("Python"));
        setOfSkills.add(new Skill("SQL"));

        // Act
        Team team = new Team(numberOfSkills, setOfSkills);

        // Assert
        assertEquals(numberOfSkills, team.getNumberOfSkills());
        assertEquals(setOfSkills, team.getSetOfSkills());
    }

    @Test
    public void testConstructorWithNegativeNumberOfSkills() {
        // Arrange
        int numberOfSkills = -3;
        ArrayList<Skill> setOfSkills = new ArrayList<>();
        setOfSkills.add(new Skill("Java"));
        setOfSkills.add(new Skill("Python"));
        setOfSkills.add(new Skill("SQL"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Team(numberOfSkills, setOfSkills));
    }

    @Test
    public void testConstructorWithNullSetOfSkills() {
        // Arrange
        int numberOfSkills = 3;
        ArrayList<Skill> setOfSkills = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Team(numberOfSkills, setOfSkills));
    }

    @Test
    public void testConstructorWithEmptySetOfSkills() {
        // Arrange
        int numberOfSkills = 0;
        ArrayList<Skill> setOfSkills = new ArrayList<>();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Team(numberOfSkills, setOfSkills));
    }

    @Test
    public void testClone() {
        // Arrange
        int numberOfSkills = 3;
        ArrayList<Skill> setOfSkills = new ArrayList<>();
        setOfSkills.add(new Skill("Java"));
        setOfSkills.add(new Skill("Python"));
        setOfSkills.add(new Skill("SQL"));
        Team team = new Team(numberOfSkills, setOfSkills);

        // Act
        Team clonedTeam = team.clone();

        // Assert
        assertNotSame(team, clonedTeam);
        assertEquals(team.getNumberOfSkills(), clonedTeam.getNumberOfSkills());
        assertEquals(team.getSetOfSkills(), clonedTeam.getSetOfSkills());
    }

    @Test
    public void testToString() {
        // Arrange
        int numberOfSkills = 3;
        ArrayList<Skill> setOfSkills = new ArrayList<>();
        setOfSkills.add(new Skill("Java"));
        setOfSkills.add(new Skill("Python"));
        setOfSkills.add(new Skill("SQL"));
        Team team = new Team(numberOfSkills, setOfSkills);

        // Act
        String toString = team.toString();

        // Assert
        assertTrue(toString.contains("numberOfSkills=" + numberOfSkills));
        assertTrue(toString.contains("setOfSkills=" + setOfSkills));
    }
}
