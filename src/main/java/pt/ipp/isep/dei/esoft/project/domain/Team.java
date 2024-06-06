package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Team implements Serializable {
    private final ArrayList<Skill> setOfSkills;
    private int maxSize;
    private int minSize;

    public Team(ArrayList<Skill> setOfSkills, int maxSize, int minSize) {
        this.setOfSkills = setOfSkills;
        this.maxSize = maxSize;
        this.minSize = minSize;
    }


    public ArrayList<Skill> getSetOfSkills() {
        return setOfSkills;
    }

    public int getMaxSize() {return maxSize;}

    public int getMinSize() {return minSize;}

    public void setMaxSize(int maxSize) {this.maxSize = maxSize;}

    public void setMinSize(int minSize) {this.minSize = minSize;}

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
        return Objects.hash(setOfSkills);
    }

    @Override
    public String toString() {
        return "Team{" +
                ", setOfSkills=" + setOfSkills +
                ", maxSize = " + maxSize +
                ", minSize = " + minSize +
                '}';
    }

}
