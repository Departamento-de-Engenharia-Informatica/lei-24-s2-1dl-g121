package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillRepository {

    private final List<Skill> skills;

    public SkillRepository() {
        skills = new ArrayList<>();
    }

    /**
     * This method returns an existing Skill by its name. If the skill does not exist,
     * it adds the skill to the list.
     *
     * @param skillName The name of the skill to be retrieved or added.
     * @return The skill.
     */
    public Skill getSkillByName(String skillName) {
        Skill newSkill = new Skill(skillName);
        Skill skill = null;
        if (skills.contains(newSkill)) {
            skill = skills.get(skills.indexOf(newSkill));
            System.out.println("Skill [" + skillName + "] already exists.");
        } else {
            skill = newSkill.clone();
            skills.add(skill);
            System.out.println("Skill [" + skillName + "] added.");
        }
        return skill;
    }

    /**
     * Adds a new skill to the repository.
     *
     * @param skill The skill to be added.
     * @return An optional containing the added skill if it was added successfully, empty otherwise.
     */
    public Optional<Skill> add(Skill skill) {

        Optional<Skill> newSkill = Optional.empty();
        boolean operationSuccess = false;

        if (validateSkill(skill)) {
            newSkill = Optional.of(skill.clone());
            operationSuccess = skills.add(newSkill.get());
        }

        if (!operationSuccess) {
            newSkill = Optional.empty();
        }

        return newSkill;
    }

    private boolean validateSkill(Skill skill) {
        boolean isValid = !skills.contains(skill);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of skills.
     *
     * @return The list of skills.
     */
    public List<Skill> getSkills() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(skills);
    }
}


