package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Objects;

public class Team {
    private final int numberOfSkills;
    private final ArrayList<Skill> setOfSkills;

    public Team(int numberOfSkills, ArrayList<Skill> setOfSkills) {
        this.numberOfSkills = numberOfSkills;
        this.setOfSkills = setOfSkills;
    }
    private void validateNumberOfSkills(int numberOfSkills) {
        if (numberOfSkills < 0) {
            throw new IllegalArgumentException("Number of skills cannot be negative.");
        }
    }

    private void validateSetOfSkills(ArrayList<Skill> setOfSkills) {
        if (setOfSkills == null || setOfSkills.isEmpty()) {
            throw new IllegalArgumentException("Set of skills cannot be null or empty.");
        }
    }
    public int getNumberOfSkills() {
        return numberOfSkills;
    }

    public ArrayList<Skill> getSetOfSkills() {
        return setOfSkills;
    }
    public Team clone() {
        try {
            return (Team) super.clone();
        } catch (CloneNotSupportedException e) {
            // This should never happen since Job implements Cloneable
            throw new AssertionError();
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(numberOfSkills, setOfSkills);
    }

    @Override
    public String toString() {
        return "Team{" +
                ", numberOfSkills=" + numberOfSkills +
                ", setOfSkills=" + setOfSkills +
                '}';
    }

}
