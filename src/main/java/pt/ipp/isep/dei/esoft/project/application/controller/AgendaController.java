package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AgendaController {
    private Agenda agenda;
    private ToDoList toDoList;
    private TeamRepository teamRepository;
    private AuthenticationRepository authenticationRepository;

    public AgendaController() {
        getAgendaRepository();
        getToDoListRepository();
        getTeamRepository();
        getAuthenticationRepository();
    }

    private void getTeamRepository() {
        if (teamRepository == null) {
            Repositories repositories = Repositories.getInstance();
            teamRepository = repositories.getTeamRepository();
        }
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

    public AgendaController(Agenda agenda, ToDoList toDoList, TeamRepository teamRepository) {
        this.agenda = agenda;
        this.toDoList = toDoList;
        this.teamRepository = teamRepository;
    }

    public Optional<Entry> registerEntry(String ID, Task task, Date dueDate, status status) {
        Entry newEntry = new Entry(ID, task, dueDate, status);
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

    public List<String> getEntriesList() {
        return agenda.getEntriesIDs();
    }

    public List<String> presentEntries() {
        return agenda.getEntriesIdAndDate();
    }

    public List<String> presentEntriesByTeamAndDateRange(Team team, Date startDate, Date endDate) {
        List<String> lstEntries = new ArrayList<>();
        System.out.println("Team: " + team);
        System.out.println("Start date: " + startDate);
        System.out.println("End date: " + endDate);
        for (Entry entry : this.agenda.getEntries()) {
            System.out.println("Current entry: " + entry);
            System.out.println("Entry's team: " + entry.getTeam());
            System.out.println("Entry's due date: " + entry.getDueDate());
            if (entry.getTeam().equals(team) &&
                    (entry.getDueDate().equals(startDate) || entry.getDueDate().after(startDate)) &&
                    (entry.getDueDate().equals(endDate) || entry.getDueDate().before(endDate))) {
                System.out.println("Entry added to the list.");
                lstEntries.add(entry.getIdAndDate());
            } else {
                System.out.println("Entry not added to the list.");
            }
        }
        System.out.println("Final list of entries: " + lstEntries);
        return lstEntries;
    }

    public boolean removeEntry(String ID) {
        Optional<Entry> entryToRemove = agenda.getEntries().stream()
                .filter(entry -> entry.getID().equals(ID))
                .findFirst();

        if (entryToRemove.isPresent()) {
            try {
                agenda.remove(entryToRemove.get());
                return true;
            } catch (UnsupportedOperationException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
        return false;
    }

    public boolean assignTeamToEntry (String teamName, String entryID) {
        boolean result = false;
        Team team = teamRepository.getTeamByReference(teamName);
        Entry entry = agenda.getEntryByID(entryID);
        if (team != null && entry != null) {
            entry.setTeam(team);
            result = true;
        }
        return result;
    }
}
