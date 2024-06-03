package pt.ipp.isep.dei.esoft.project.domain;

public class Entry {
    private String ID;
    private Task task;
    // private Team team;
    //private Vehicle vehicle;
    private int duration;
    private status status;

    public Entry(String ID, Task task, int duration, status status) {
        this.ID = ID;
        this.task = task;
        this.duration = duration;
        this.status = status;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getID() {
        return ID;
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
        return new Entry(ID, task, duration, status);
    }
}
