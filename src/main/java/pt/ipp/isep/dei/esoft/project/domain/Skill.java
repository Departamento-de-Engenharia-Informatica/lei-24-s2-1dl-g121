package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Skill {
    private final String name;

    public Skill (String name){
        this.name = name;
    }
//    private void validateName(String name) {
//        if (name == null || name.isEmpty()) {
//            throw new IllegalArgumentException("Name cannot be null or empty.");
//        }
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Skill)) {
            return false;
        }
        Skill that = (Skill) o;
        return name.equals(that.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
    public Skill clone() {
        return new Skill(this.name);
    }

}
