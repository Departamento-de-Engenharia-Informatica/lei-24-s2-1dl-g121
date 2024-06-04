package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.urgencyDegree;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

import java.util.List;
import java.util.Optional;

public class ToDoListController {
    private ToDoList toDoList;
    private AuthenticationRepository authenticationRepository;

    public ToDoListController() {
        getToDoListRepository();
        getAuthenticationRepository();
    }

    public ToDoListController(ToDoList toDoListRepository) {
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

    public boolean removeTask(String reference) {
        Optional<Task> taskToRemove = toDoList.getTasks().stream()
                .filter(task -> task.getReference().equals(reference))
                .findFirst();

        if (taskToRemove.isPresent()) {
            try {
                toDoList.remove(taskToRemove.get());
                return true;
            } catch (UnsupportedOperationException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
        return false;
    }

    public Task getTaskByReference(String reference) {
        Optional<Task> task = toDoList.getTasks().stream()
                .filter(t -> t.getReference().equals(reference))
                .findFirst();
        return task.orElse(null);
    }

    public List<String> presentTasks() {
        return toDoList.getTasksAndGreenSpaces();
    }
}
