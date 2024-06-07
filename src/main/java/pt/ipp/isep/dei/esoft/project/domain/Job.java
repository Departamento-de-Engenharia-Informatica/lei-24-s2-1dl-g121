package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class Job implements Serializable {
    private final String name;

    public Job(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Job getJob() {
        return this;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Job{ name = " + name +" }";
    }


}
