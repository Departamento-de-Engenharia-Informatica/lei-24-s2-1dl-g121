package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.status;
import pt.ipp.isep.dei.esoft.project.domain.urgencyDegree;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AgendaTest {

    private Agenda agenda;
    private Entry entry;
    private Task task;
    private Date dueDate;

    @BeforeEach
    public void setUp() {
        agenda = new Agenda();
        task = new Task("Task1", "Description1",2, urgencyDegree.PENDING);
        dueDate = new Date();
        entry = new Entry("ID1", task, dueDate, status.PLANNED);
    }

    @Test
    public void shouldAddEntry() {
        Optional<Entry> addedEntry = agenda.add(entry);
        assertTrue(addedEntry.isPresent());
        assertEquals(entry, addedEntry.get());
    }

    @Test
    public void shouldNotAddDuplicateEntry() {
        agenda.add(entry);
        Optional<Entry> addedEntry = agenda.add(entry);
        assertFalse(addedEntry.isPresent());
    }

    @Test
    public void shouldGetEntriesByStatus() {
        agenda.add(entry);
        List<Entry> entries = agenda.getEntriesByStatus(status.PLANNED);
        assertEquals(1, entries.size());
        assertEquals(entry, entries.get(0));
    }

    @Test
    public void shouldGetEntryByID() {
        agenda.add(entry);
        Entry foundEntry = agenda.getEntryByID("ID1");
        assertEquals(entry, foundEntry);
    }

    @Test
    public void shouldGetEntriesByTask() {
        agenda.add(entry);
        List<Entry> entries = agenda.getEntriesByTask(task);
        assertEquals(1, entries.size());
        assertEquals(entry, entries.get(0));
    }

    @Test
    public void shouldGetEntriesIDs() {
        agenda.add(entry);
        List<String> ids = agenda.getEntriesIDs();
        assertEquals(1, ids.size());
        assertEquals("ID1", ids.get(0));
    }

    @Test
    public void shouldRemoveEntry() {
        agenda.add(entry);
        boolean isRemoved = agenda.remove(entry);
        assertTrue(isRemoved);
        assertEquals(0, agenda.getEntries().size());
    }

    @Test
    public void shouldNotRemoveNonExistentEntry() {
        boolean isRemoved = agenda.remove(entry);
        assertFalse(isRemoved);
    }
}