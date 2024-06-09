package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.urgencyDegree;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {

    private ToDoList toDoList;
    private Task task;

    @BeforeEach
    public void setUp() {
        toDoList = new ToDoList();
        task = new Task("Task1", "Description1", 10, urgencyDegree.HIGH, null);
    }

    @Test
    public void shouldAddTask() {
        Optional<Task> addedTask = toDoList.add(task);
        assertTrue(addedTask.isPresent());
        assertEquals(task, addedTask.get());
    }

    @Test
    public void shouldNotAddDuplicateTask() {
        toDoList.add(task);
        Optional<Task> addedTask = toDoList.add(task);
        assertFalse(addedTask.isPresent());
    }

    @Test
    public void shouldGetTasksByUrgencyDegree() {
        toDoList.add(task);
        List<Task> tasks = toDoList.getTasksByUrgencyDegree(urgencyDegree.HIGH);
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void shouldGetTasksReferences() {
        toDoList.add(task);
        List<String> references = toDoList.getTasksReferences();
        assertEquals(1, references.size());
        assertEquals("Task1", references.get(0));
    }

    @Test
    public void shouldRemoveTask() {
        toDoList.add(task);
        boolean isRemoved = toDoList.remove(task);
        assertTrue(isRemoved);
        assertEquals(0, toDoList.getTasks().size());
    }

    @Test
    public void shouldNotRemoveNonExistentTask() {
        boolean isRemoved = toDoList.remove(task);
        assertFalse(isRemoved);
    }

    @Test
    public void shouldGetTasksAndGreenSpaces() {
        toDoList.add(task);
        List<String> tasksAndGreenSpaces = toDoList.getTasksAndGreenSpaces();
        assertEquals(1, tasksAndGreenSpaces.size());
        assertEquals("Task1 - Any GreenSpace", tasksAndGreenSpaces.get(0));
    }
}
