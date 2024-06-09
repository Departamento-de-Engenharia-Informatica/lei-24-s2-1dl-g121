package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntryTest {

    private Entry entry;
    private Task task;
    private Team team;
    private Date dueDate;

    @BeforeEach
    public void setUp() {
        task = new Task("Task1", "Description1",2, urgencyDegree.PENDING);
        List<Collaborator> team1 = new ArrayList<>();
        team1.add(new Collaborator("Jorge", "2019-01-01", "ns", "alias", "email", "phone", "10012", "00100100", new Job("SoftwareDeveloper")));
        team1.add(new Collaborator("João", "2019-01-01", "ns", "alias", "email", "phone", "10012", "00100100", new Job("SoftwareDeveloper")));
        team = new Team(team1, "Team1");
        dueDate = new Date(2024, 6, 3);
        entry = new Entry("ID1", task, dueDate, status.PLANNED);
    }

    @Test
    public void shouldSetAndGetID() {
        entry.setID("ID2");
        assertEquals("ID2", entry.getID());
    }

    @Test
    public void shouldSetAndGetDuration() {
        Date newDueDate = new Date(2024,12,1);
        entry.setDuration(newDueDate);
        assertEquals(newDueDate, entry.getDueDate());
    }

    @Test
    public void shouldSetAndGetStatus() {
        entry.setStatus(status.DONE);
        assertEquals(status.DONE, entry.getStatus());
    }

    @Test
    public void shouldSetAndGetTeam() {
        entry.setTeam(team);
        assertEquals(team, entry.getTeam());
    }

    @Test
    public void shouldCloneEntry() {
        Entry clonedEntry = entry.clone();
        assertNotNull(clonedEntry);
        assertEquals(entry.getID(), clonedEntry.getID());
        assertEquals(entry.getTask(), clonedEntry.getTask());
        assertEquals(entry.getDueDate(), clonedEntry.getDueDate());
        assertEquals(entry.getStatus(), clonedEntry.getStatus());
    }
}