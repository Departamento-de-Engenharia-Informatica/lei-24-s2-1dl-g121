package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Agenda;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

import java.util.List;
import java.util.Optional;

public class AgendaController {
    private Agenda agenda;
    private ToDoList toDoList;
    private AuthenticationRepository authenticationRepository;

    public AgendaController() {
        getAgendaRepository();
        getToDoListRepository();
        getAuthenticationRepository();
    }

    private void getAgendaRepository() {
        if (agenda == null) {
            Repositories repositories = Repositories.getInstance();
            agenda = repositories.getAgenda();
        }
    }

    private void getToDoListRepository() {
        if (toDoList == null) {
            Repositories repositories = Repositories.getInstance();
            toDoList = repositories.getToDoList();
        }
    }

    private void getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
    }

    public AgendaController(Agenda agenda, ToDoList toDoList) {
        this.agenda = agenda;
        this.toDoList = toDoList;
    }

    public Optional<Entry> registerEntry(String ID, Task task, int duration, status status) {
        Entry newEntry = new Entry(ID, task, duration, status);
        if (!agenda.getEntries().contains(newEntry)) {
            try{
                agenda.add(newEntry);
            } catch (UnsupportedOperationException e){
                System.out.println("Error:" + e.getMessage());
            }
            return Optional.of(newEntry);
        }
        return Optional.empty();
    }

    public List<String> getEntries() {
        return agenda.getEntriesIDs();
    }
}
