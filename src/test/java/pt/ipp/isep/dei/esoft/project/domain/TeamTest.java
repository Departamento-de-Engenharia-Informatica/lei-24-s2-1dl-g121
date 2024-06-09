//package pt.ipp.isep.dei.esoft.project.domain;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TeamTest {
//
//    @Test
//    public void testConstructorAndGetters() {
//        // Arrange
//        int numberOfSkills = 3;
//        ArrayList<Skill> setOfSkills = new ArrayList<>();
//        setOfSkills.add(new Skill("Java"));
//        setOfSkills.add(new Skill("Python"));
//        setOfSkills.add(new Skill("SQL"));
//        int maxSize = 3;
//        int minSize = 1;
//
//        // Act
//        Team team = new Team(setOfSkills, maxSize, minSize);
//
//        // Assert
//        assertEquals(setOfSkills, team.getSetOfSkills());
//    }
//
//    @Test
//    public void testConstructorWithNegativeNumberOfSkills() {
//        // Arrange
//        int numberOfSkills = -3;
//        ArrayList<Skill> setOfSkills = new ArrayList<>();
//        setOfSkills.add(new Skill("Java"));
//        setOfSkills.add(new Skill("Python"));
//        setOfSkills.add(new Skill("SQL"));
//        int maxSize = 3;
//        int minSize = 1;
//
//        // Act & Assert
//        assertThrows(IllegalArgumentException.class, () -> new Team(setOfSkills, maxSize, minSize));
//    }
//
//    @Test
//    public void testConstructorWithNullSetOfSkills() {
//        // Arrange
//        int numberOfSkills = 3;
//        ArrayList<Skill> setOfSkills = null;
//        int maxSize = 3;
//        int minSize = 1;
//
//        // Act & Assert
//        assertThrows(IllegalArgumentException.class, () -> new Team(setOfSkills, maxSize, minSize));
//    }
//
//    @Test
//    public void testConstructorWithEmptySetOfSkills() {
//        // Arrange
//        int numberOfSkills = 0;
//        ArrayList<Skill> setOfSkills = new ArrayList<>();
//        int maxSize = 3;
//        int minSize = 1;
//
//        // Act & Assert
//        assertThrows(IllegalArgumentException.class, () -> new Team(setOfSkills, maxSize, minSize));
//    }
//
//    @Test
//    public void testClone() {
//        // Arrange
//        int numberOfSkills = 3;
//        ArrayList<Skill> setOfSkills = new ArrayList<>();
//        setOfSkills.add(new Skill("Java"));
//        setOfSkills.add(new Skill("Python"));
//        setOfSkills.add(new Skill("SQL"));
//        int maxSize = 3;
//        int minSize = 1;
//        Team team = new Team(setOfSkills, maxSize, minSize);
//
//        // Act
//        Team clonedTeam = team.clone();
//
//        // Assert
//        assertNotSame(team, clonedTeam);
//        assertEquals(team.getSetOfSkills(), clonedTeam.getSetOfSkills());
//    }
//
//    @Test
//    public void testToString() {
//        // Arrange
//        int numberOfSkills = 3;
//        ArrayList<Skill> setOfSkills = new ArrayList<>();
//        setOfSkills.add(new Skill("Java"));
//        setOfSkills.add(new Skill("Python"));
//        setOfSkills.add(new Skill("SQL"));
//        int maxSize = 3;
//        int minSize = 1;
//        Team team = new Team(setOfSkills, maxSize, minSize);
//
//        // Act
//        String toString = team.toString();
//
//        // Assert
//        assertTrue(toString.contains("numberOfSkills=" + numberOfSkills));
//        assertTrue(toString.contains("setOfSkills=" + setOfSkills));
//    }
//}
