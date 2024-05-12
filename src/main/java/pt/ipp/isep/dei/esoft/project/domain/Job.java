package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class Job {
    private final String name;
//    private final int numberOfSkills;
//    private final ArrayList<Skill> setOfSkills;

    public Job(String name) {
        validateName(name);
//        validateNumberOfSkills(numberOfSkills);
//        validateSetOfSkills(setOfSkills);
        this.name = name;
//        this.numberOfSkills = numberOfSkills;
//        this.setOfSkills = setOfSkills;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (!name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Skill name cannot contain special characters or digits.");
        }
    }

//    private void validateNumberOfSkills(int numberOfSkills) {
//        if (numberOfSkills < 0) {
//            throw new IllegalArgumentException("Number of skills cannot be negative.");
//        }
//    }
//
//    private void validateSetOfSkills(ArrayList<Skill> setOfSkills) {
//        if (setOfSkills == null || setOfSkills.isEmpty()) {
//            throw new IllegalArgumentException("Set of skills cannot be null or empty.");
//        }
//    }

    public String getName() {
        return name;
    }

    public Job getJob() {
        return this;
    }

//    public int getNumberOfSkills() {
//        return numberOfSkills;
//    }

//    public ArrayList<Skill> getSetOfSkills() {
//        return setOfSkills;
//    }
//    public Job clone() {
//        try {
//            return (Job) super.clone();
//        } catch (CloneNotSupportedException e) {
//            // This should never happen since Job implements Cloneable
//            throw new AssertionError();
//        }
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Job)) return false;
//        Job job = (Job) o;
//        return numberOfSkills == job.numberOfSkills &&
//                name.equals(job.name) &&
////                setOfSkills.equals(job.setOfSkills);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
//                ", numberOfSkills=" + numberOfSkills +
//                ", setOfSkills=" + setOfSkills +
                '}';
    }

}
