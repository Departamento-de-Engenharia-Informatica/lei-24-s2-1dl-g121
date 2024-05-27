package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.TaskEntry;
import pt.ipp.isep.dei.esoft.project.domain.urgencyDegree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoListRepository {

    private final List<TaskEntry> tasks;
    public ToDoListRepository() {
        tasks = new ArrayList<>();
    }

    public Optional<TaskEntry> add(TaskEntry task) {

        Optional<TaskEntry> newTask = Optional.empty();
        boolean operationSuccess = false;

        if (validateTask(task)) {
            newTask = Optional.of(task.clone());
            operationSuccess = tasks.add(newTask.get());
        }

        if (!operationSuccess) {
            newTask = Optional.empty();
        }

        return newTask;
    }

    private boolean validateTask(TaskEntry task) {
        boolean isValid = !tasks.contains(task);
        return isValid;
    }

    public List<TaskEntry> getTasks() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(tasks);
    }

    public List<TaskEntry> getTasksByUrgencyDegree(urgencyDegree urgencyDegree) {
        List<TaskEntry> tasksByUrgencyDegree = new ArrayList<>();
        for (TaskEntry task : tasks) {
            if (task.getUrgencyDegree().equals(urgencyDegree)) {
                tasksByUrgencyDegree.add(task);
            }
        }
        return tasksByUrgencyDegree;
    }
}