package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Skill {
    private final String skillName;

    public Skill (String name){
        validateName(name);
        this.skillName = name;
    }
    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be null or empty.");
        }
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Skill name cannot contain special characters or digits.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Skill)) {
            return false;
        }
        Skill that = (Skill) o;
        return skillName.equals(that.skillName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(skillName);
    }

    public String getSkillName() {
        return skillName;
    }
    public Skill clone() {
        return new Skill(this.skillName);
    }

}
