package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class TaskCategoryModelo {

    private final String description;

    public TaskCategoryModelo(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskCategoryModelo)) {
            return false;
        }
        TaskCategoryModelo that = (TaskCategoryModelo) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    /**
     * This method returns the description of the task category.
     *
     * @return The description of the task category.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current task.
     */
    public TaskCategoryModelo clone() {
        return new TaskCategoryModelo(this.description);
    }
}