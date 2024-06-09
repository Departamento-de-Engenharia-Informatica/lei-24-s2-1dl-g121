package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.urgencyDegree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskTest {

    private Task task;
    private GreenSpaces greenSpace;

    @BeforeEach
    public void setUp() {
        greenSpace = new GreenSpaces("garden", 100, "Praça de 9 de Abril 121, 4200-422 Porto", "ArcaAgua", "exemplo@exemplo.com");
        task = new Task("Task1", "Description1", 10, urgencyDegree.HIGH, greenSpace);
    }

    @Test
    public void shouldSetAndGetDescription() {
        task.setDescription("Description2");
        assertEquals("Description2", task.getDescription());
    }

    @Test
    public void shouldSetAndGetDuration() {
        task.setDuration(20);
        assertEquals(20, task.getDuration());
    }

    @Test
    public void shouldSetAndGetUrgencyDegree() {
        task.setUrgencyDegree(urgencyDegree.LOW);
        assertEquals(urgencyDegree.LOW, task.getUrgencyDegree());
    }

    @Test
    public void shouldSetAndGetGreenSpace() {
        GreenSpaces greenSpace2 = new GreenSpaces("garden", 200, "Praça de 10 de Abril 122, 4200-423 Porto", "Parque da Cidade", "exemplo@exemplo.com");
        task.setGreenSpace(greenSpace2);
        assertEquals(greenSpace2, task.getGreenSpace());
    }

    @Test
    public void shouldGetTaskAndGreenSpace() {
        String expected = "Task1 - ArcaAgua";
        assertEquals(expected, task.getTaskAndGreenSpace());
    }

    @Test
    public void shouldGetTaskAndGreenSpaceWhenGreenSpaceIsNull() {
        task.setGreenSpace(null);
        String expected = "Task1 - Any GreenSpace";
        assertEquals(expected, task.getTaskAndGreenSpace());
    }

    @Test
    public void shouldCloneTask() {
        Task clonedTask = task.clone();
        assertNotNull(clonedTask);
        assertEquals(task.getReference(), clonedTask.getReference());
        assertEquals(task.getDescription(), clonedTask.getDescription());
        assertEquals(task.getDuration(), clonedTask.getDuration());
        assertEquals(task.getUrgencyDegree(), clonedTask.getUrgencyDegree());
        assertEquals(task.getGreenSpace(), clonedTask.getGreenSpace());
    }
}
