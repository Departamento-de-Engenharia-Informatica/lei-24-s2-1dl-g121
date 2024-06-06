package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Date;

public class Entry implements Serializable {
    private String ID;
    private Task task;
    // private Team team;
    //private Vehicle vehicle;
    private Date dueDate;
    private status status;

    public Entry(String ID, Task task, Date dueDate, status status) {
        this.ID = ID;
        this.task = task;
        this.dueDate = dueDate;
        this.status = status;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setDuration(Date dueDate) {
        this.dueDate = dueDate;
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

    public Date getDuration() {
        return dueDate;
    }

    public status getStatus() {
        return status;
    }

    public String toString() {
        return String.format("Entry: %s, %s, %s", task, dueDate.toString(), status);
    }


    public String getIdAndDate() {
        return String.format("%s - %d/%d/%d", ID, dueDate.getYear(), dueDate.getMonth(), dueDate.getDate());
    }

    /**
     * Clone method.
     *
     * @return copy of the object
     */
    public Entry clone() {
        return new Entry(ID, task, dueDate, status);
    }
}
