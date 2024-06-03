package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.status;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

import java.util.Optional;

public class CreateEntryController {
    private Agenda agenda;
    private ToDoList toDoList;

    public CreateEntryController() {
        getAgenda();
        getToDoList();
    }

    public CreateEntryController(Agenda agenda, ToDoList toDoList) {
        this.agenda = agenda;
        this.toDoList = toDoList;
    }

    private ToDoList getToDoList() {
        if (toDoList == null) {
            Repositories repositories = Repositories.getInstance();
            toDoList = repositories.getToDoList();
        }
        return toDoList;
    }

    private Agenda getAgenda() {
        if (agenda == null) {
            Repositories repositories = Repositories.getInstance();
            agenda = repositories.getAgenda();
        }
        return agenda;
    }

    public Optional<Entry> registerEntry(String ID, Task task, int duration, status status) {
        Entry newEntry = new Entry(ID, task, duration, status);
        if (!agenda.getEntries().contains(newEntry) && !agenda.getEntriesByTask(task).isEmpty()) {
            try {
                agenda.add(newEntry);
            } catch (UnsupportedOperationException e) {
                System.out.println("Error:" + e.getMessage());
            }
            return Optional.of(newEntry);
        }
        return Optional.empty();
    }
}
