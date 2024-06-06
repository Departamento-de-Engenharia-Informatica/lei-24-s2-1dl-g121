package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Task;
import pt.ipp.isep.dei.esoft.project.domain.urgencyDegree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoList implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Task> tasks;

    public ToDoList() {
        tasks = new ArrayList<>();
    }

    public Optional<Task> add(Task task) {

        Optional<Task> newTask = Optional.empty();
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

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    private boolean validateTask(Task task) {
        boolean isValid = !tasks.contains(task);
        return isValid;
    }

    public List<Task> getTasks() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(tasks);
    }

    public List<Task> getTasksByUrgencyDegree(urgencyDegree urgencyDegree) {
        List<Task> tasksByUrgencyDegree = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getUrgencyDegree().equals(urgencyDegree)) {
                tasksByUrgencyDegree.add(task);
            }
        }
        return tasksByUrgencyDegree;
    }

    public List<String> getTasksReferences() {
        List<String> references = new ArrayList<>();
        for (Task task : tasks) {
            references.add(task.getReference());
        }
        return references;
    }

    public boolean remove(Task task) {
        return tasks.remove(task);
    }

    public List<String> getTasksAndGreenSpaces() {
        List<String> tasksAndGreenSpaces = new ArrayList<>();
        for (Task task : tasks) {
            tasksAndGreenSpaces.add(task.getTaskAndGreenSpace());
        }
        return tasksAndGreenSpaces;
    }
}