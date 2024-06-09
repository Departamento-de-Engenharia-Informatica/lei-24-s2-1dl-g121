package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {
    private final String reference;
    private String description;
    private int duration;
    private urgencyDegree urgencyDegree;
    private GreenSpaces greenSpace;

    public Task(String reference, String description, int duration, urgencyDegree urgencyDegree, GreenSpaces greenSpace) {
        this.reference = reference;
        this.description = description;
        this.duration = duration;
        this.urgencyDegree = urgencyDegree;
        this.greenSpace = greenSpace;
    }

    public Task(String reference, String description, int duration, urgencyDegree urgencyDegree) {
        this.reference = reference;
        this.description = description;
        this.duration = duration;
        this.urgencyDegree = urgencyDegree;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setUrgencyDegree(urgencyDegree urgencyDegree) {
        this.urgencyDegree = urgencyDegree;
    }

    public void setGreenSpace(GreenSpaces greenSpace) {
        this.greenSpace = greenSpace;
    }

    public String getReference() {
        return reference;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public urgencyDegree getUrgencyDegree() {
        return urgencyDegree;
    }

    public GreenSpaces getGreenSpace() {
        return greenSpace;
    }

    public String getTaskAndGreenSpace() {
        if (this.greenSpace == null) {
            return String.format("%s - Any GreenSpace", reference);
        }
        return String.format("%s - %s", reference, greenSpace.getName());
    }

    public String toString() {
        return String.format("Task: %s, %s, %d, %s", reference, description, duration, urgencyDegree);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return duration == task.duration &&
                urgencyDegree == task.urgencyDegree &&
                Objects.equals(reference, task.reference) &&
                Objects.equals(description, task.description) &&
                Objects.equals(greenSpace, task.greenSpace);
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Task clone() {
        return new Task(this.reference, this.description, this.duration, this.urgencyDegree, this.greenSpace);
    }
}