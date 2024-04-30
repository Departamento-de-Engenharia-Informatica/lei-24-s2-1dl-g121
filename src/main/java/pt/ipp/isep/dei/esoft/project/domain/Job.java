package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;
import java.util.Set;

public class Job {
    private final String name;
    private final int numberOfSkills;
    private final Set<String> setOfSkills;

    public Job(String name, int numberOfSkills, Set<String> setOfSkills) {
        validateName(name);
        validateNumberOfSkills(numberOfSkills);
        validateSetOfSkills(setOfSkills);
        this.name = name;
        this.numberOfSkills = numberOfSkills;
        this.setOfSkills = setOfSkills;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
    }

    private void validateNumberOfSkills(int numberOfSkills) {
        if (numberOfSkills < 0) {
            throw new IllegalArgumentException("Number of skills cannot be negative.");
        }
    }

    private void validateSetOfSkills(Set<String> setOfSkills) {
        if (setOfSkills == null || setOfSkills.isEmpty()) {
            throw new IllegalArgumentException("Set of skills cannot be null or empty.");
        }
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSkills() {
        return numberOfSkills;
    }

    public Set<String> getSetOfSkills() {
        return setOfSkills;
    }
    public Job clone() {
        try {
            return (Job) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since Job implements Cloneable
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;
        Job job = (Job) o;
        return numberOfSkills == job.numberOfSkills &&
                name.equals(job.name) &&
                setOfSkills.equals(job.setOfSkills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfSkills, setOfSkills);
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", numberOfSkills=" + numberOfSkills +
                ", setOfSkills=" + setOfSkills +
                '}';
    }
}