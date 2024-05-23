package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class TaskModelo {
    private final String reference;
    private String description;
    private String informalDescription;
    private String technicalDescription;
    private int duration;
    private double cost;

    private TaskCategoryModelo taskCategory;

    private Employee employee;

    public TaskModelo(String reference, String description, String informalDescription, String technicalDescription,
                      int duration, double cost, TaskCategoryModelo taskCategory, Employee employee) {

        validateReference(reference);
        this.reference = reference;
        this.description = description;
        this.informalDescription = informalDescription;
        this.technicalDescription = technicalDescription;
        this.duration = duration;
        this.cost = cost;
        this.taskCategory = taskCategory;
        this.employee = employee;
    }

    private void validateReference(String reference) {
        //TODO: missing from the diagrams
        if (reference == null || reference.isEmpty()) {
            throw new IllegalArgumentException("Reference cannot be null or empty.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskModelo)) {
            return false;
        }
        TaskModelo task = (TaskModelo) o;
        return reference.equals(task.reference) && employee.equals(task.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, employee);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public TaskModelo clone() {
        return new TaskModelo(this.reference, this.description, this.informalDescription, this.technicalDescription,
                this.duration, this.cost, this.taskCategory, this.employee);
    }
}