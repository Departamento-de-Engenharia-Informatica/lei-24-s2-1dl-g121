package pt.ipp.isep.dei.esoft.project.domain;

public class TaskEntry {
    private final String reference;
    private String description;
    private int duration;
    private urgencyDegree urgencyDegree;
    private GreenSpaces greenSpace;

    public TaskEntry(String reference, String description, int duration, urgencyDegree urgencyDegree, GreenSpaces greenSpace) {
        this.reference = reference;
        this.description = description;
        this.duration = duration;
        this.urgencyDegree = urgencyDegree;
        this.greenSpace = greenSpace;
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

    public String toString() {
        return String.format("Task: %s, %s, %d, %s", reference, description, duration, urgencyDegree);
    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public TaskEntry clone() {
        return new TaskEntry(this.reference, this.description, this.duration, this.urgencyDegree, this.greenSpace);
    }
}