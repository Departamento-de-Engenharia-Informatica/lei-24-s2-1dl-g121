package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Task {
    private final String reference;
    private String description;
    private int duration;
    //urgency

    public Task(String reference, String description, int duration) {

        validateReference(reference);
        this.reference = reference;
        this.description = description;
        this.duration = duration;
    }

    private void validateReference(String reference) {
        //TODO: missing from the diagrams
        if (reference == null || reference.isEmpty()) {
            throw new IllegalArgumentException("Reference cannot be null or empty.");
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof Task)) {
//            return false;
//        }
//        Task task = (Task) o;
//        return reference.equals(task.reference) && employee.equals(task.employee);
//    }

    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
    public Task clone() {
        return new Task(this.reference, this.description, this.duration);
    }
}