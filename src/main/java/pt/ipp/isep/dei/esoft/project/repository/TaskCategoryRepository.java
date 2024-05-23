package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.TaskCategoryModelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskCategoryRepository {

    private final List<TaskCategoryModelo> taskCategories;
    public TaskCategoryRepository() {
        taskCategories = new ArrayList<>();
    }

    /**
     * This method returns an exsiting Task Category by its description.
     *
     * @param taskCategoryDescription The description of the task category to be created.
     * @return The task category.
     * @throws IllegalArgumentException if the task category does not exist, which should never happen.
     */
    public TaskCategoryModelo getTaskCategoryByDescription(String taskCategoryDescription) {
        TaskCategoryModelo newTaskCategory = new TaskCategoryModelo(taskCategoryDescription);
        TaskCategoryModelo taskCategory = null;
        if (taskCategories.contains(newTaskCategory)) {
            taskCategory = taskCategories.get(taskCategories.indexOf(newTaskCategory));
        }
        if (taskCategory == null) {
            throw new IllegalArgumentException(
                    "Task Category requested for [" + taskCategoryDescription + "] does not exist.");
        }
        return taskCategory;
    }

    public Optional<TaskCategoryModelo> add(TaskCategoryModelo taskCategory) {

        Optional<TaskCategoryModelo> newTaskCategory = Optional.empty();
        boolean operationSuccess = false;

        if (validateTaskCategory(taskCategory)) {
            newTaskCategory = Optional.of(taskCategory.clone());
            operationSuccess = taskCategories.add(newTaskCategory.get());
        }

        if (!operationSuccess) {
            newTaskCategory = Optional.empty();
        }

        return newTaskCategory;
    }

    private boolean validateTaskCategory(TaskCategoryModelo taskCategory) {
        boolean isValid = !taskCategories.contains(taskCategory);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<TaskCategoryModelo> getTaskCategories() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(taskCategories);
    }
}