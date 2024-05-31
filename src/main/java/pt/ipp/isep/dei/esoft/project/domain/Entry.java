package pt.ipp.isep.dei.esoft.project.domain;

public class Entry {
    private Task task;
    //private Vehicle vehicle;
    private int duration;
    private status status;

    public Entry(Task task, int duration, status status) {
        this.task = task;
        this.duration = duration;
        this.status = status;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public Task getTask() {
        return task;
    }

    public int getDuration() {
        return duration;
    }

    public status getStatus() {
        return status;
    }

    public String toString() {
        return String.format("Entry: %s, %d, %s", task, duration, status);
    }

    /**
     * Clone method.
     *
     * @return copy of the object
     */
    public Entry clone() {
        return new Entry(task, duration, status);
    }
}
