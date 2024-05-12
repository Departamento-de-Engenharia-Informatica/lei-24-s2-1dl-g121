package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class JobTest {

    @Test
    public void testConstructor_ValidArguments_ObjectCreated() {
        // Arrange
        String name = "Software Engineer";
        int numberOfSkills = 3;
        ArrayList<Skill> setOfSkills = new ArrayList<>(Arrays.asList(
                new Skill("Java"),
                new Skill("SQL"),
                new Skill("Problem Solving")
        ));

        // Act
        Job job = new Job(name);

        // Assert
        assertNotNull(job);
        assertEquals(name, job.getName());

    }

    @Test
    public void testConstructor_NullName_ExceptionThrown() {
        // Arrange
        String name = null;
        int numberOfSkills = 3;
        ArrayList<Skill> setOfSkills = new ArrayList<>(Arrays.asList(
                new Skill("Java"),
                new Skill("SQL"),
                new Skill("Problem Solving")
        ));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Act
            new Job(name);
        });
    }

    @Test
    public void testConstructor_EmptyName_ExceptionThrown() {
        // Arrange
        String name = "";
        int numberOfSkills = 3;
        ArrayList<Skill> setOfSkills = new ArrayList<>(Arrays.asList(
                new Skill("Java"),
                new Skill("SQL"),
                new Skill("Problem Solving")
        ));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Act
            new Job(name);
        });
    }

    @Test
    public void testConstructor_NegativeNumberOfSkills_ExceptionThrown() {
        // Arrange
        String name = "Software Engineer";
        int numberOfSkills = -3;
        ArrayList<Skill> setOfSkills = new ArrayList<>(Arrays.asList(
                new Skill("Java"),
                new Skill("SQL"),
                new Skill("Problem Solving")
        ));

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Act
            new Job(name);
        });
    }

    @Test
    public void testConstructor_NullSetOfSkills_ExceptionThrown() {
        // Arrange
        String name = "Software Engineer";
        int numberOfSkills = 3;
        ArrayList<Skill> setOfSkills = null;

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Act
            new Job(name);
        });
    }

    @Test
    public void testConstructor_EmptySetOfSkills_ExceptionThrown() {
        // Arrange
        String name = "Software Engineer";
        int numberOfSkills = 3;
        ArrayList<Skill> setOfSkills = new ArrayList<>();

        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            // Act
            new Job(name);
        });
    }
}

