package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.urgencyDegree;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

import java.util.List;
import java.util.Optional;

public class CreateTaskController {
    private ToDoList toDoList;
    private AuthenticationRepository authenticationRepository;

    public CreateTaskController() {
        getToDoListRepository();
        getAuthenticationRepository();
    }

    public CreateTaskController(ToDoList toDoListRepository) {
        this.toDoList = toDoListRepository;
    }

    private ToDoList getToDoListRepository() {
        if (toDoList == null) {
            Repositories repositories = Repositories.getInstance();
            toDoList = repositories.getToDoList();
        }
        return toDoList;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    public Optional<Task> registerTask(String reference, String description, int duration, urgencyDegree urgencyDegree, GreenSpaces greenSpaces) {
        Task newTask = new Task(reference, description, duration, urgencyDegree, greenSpaces);
        if (!toDoList.getTasks().contains(newTask)) {
            try{
                toDoList.add(newTask);
            } catch (UnsupportedOperationException e){
                System.out.println("Error:" + e.getMessage());
            }
            return Optional.of(newTask);
        }
        return Optional.empty();
    }

    public List<String> getTasks() {
        return toDoList.getTasksReferences();
    }
}
