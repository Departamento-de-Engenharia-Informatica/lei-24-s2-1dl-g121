package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.urgencyDegree;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoListRepository;

import java.util.Optional;

public class CreateTaskEntryController {
    private ToDoListRepository toDoListRepository;
    private AuthenticationRepository authenticationRepository;

    public CreateTaskEntryController() {
        getToDoListRepository();
        getAuthenticationRepository();
    }

    public CreateTaskEntryController(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    private ToDoListRepository getToDoListRepository() {
        if (toDoListRepository == null) {
            Repositories repositories = Repositories.getInstance();
            toDoListRepository = repositories.getToDoListRepository();
        }
        return toDoListRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public Optional<TaskEntry> registerTaskEntry(String reference, String description, int duration, urgencyDegree urgencyDegree, GreenSpaces greenSpaces) {
        TaskEntry newTask = new TaskEntry(reference, description, duration, urgencyDegree, greenSpaces);
        if (!toDoListRepository.getTasks().contains(newTask)) {
            try{
                toDoListRepository.add(newTask);
            } catch (UnsupportedOperationException e){
                System.out.println("Error:" + e.getMessage());
            }
            return Optional.of(newTask);
        }
        return Optional.empty();
    }
}
