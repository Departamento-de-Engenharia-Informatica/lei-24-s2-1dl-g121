package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.List;

/**
 * Controller for assigning skills to collaborators.
 * This controller handles the retrieval of collaborators and skills from their respective repositories,
 * and the assignment of a skill to a collaborator.
 */
public class AssignSkillController {
    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;

    /**
     * Default constructor that initializes the repositories from the singleton instance.
     */
    public AssignSkillController() {
        getCollaboratorRepository();
        getSkillRepository();
    }

    /**
     * Constructor that allows the injection of repositories.
     *
     * @param collaboratorRepository the collaborator repository to use
     * @param skillRepository        the skill repository to use
     */
    public AssignSkillController(CollaboratorRepository collaboratorRepository, SkillRepository skillRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.skillRepository = skillRepository;
    }

    /**
     * Retrieves the collaborator repository from the singleton instance if it's not already set.
     *
     * @return the collaborator repository
     */
    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    /**
     * Retrieves the skill repository from the singleton instance if it's not already set.
     *
     * @return the skill repository
     */
    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            skillRepository = Repositories.getInstance().getSkillRepository();
        }
        return skillRepository;
    }

    /**
     * Retrieves the list of all collaborators from the collaborator repository.
     *
     * @return the list of all collaborators
     */
    public List<Collaborator> getCollaboratorList() {
        return collaboratorRepository.getCollaborators();
    }

    /**
     * Retrieves the list of all skills from the skill repository.
     *
     * @return the list of all skills
     */
    public List<Skill> getSkillList() {
        return skillRepository.getSkills();
    }

    /**
     * Assigns a skill to a collaborator.
     *
     * @param ID        the ID of the collaborator
     * @param skillName the name of the skill
     */
    public void assignSkill(String ID, String skillName) {
        Collaborator collaborator = collaboratorRepository.getCollaboratorById(ID);
        Skill skill = skillRepository.getSkillByName(skillName);
        collaborator.addSkill(skill);
    }

    /**
     * Verifies if a collaborator with the given ID exists.
     *
     * @param id the ID of the collaborator
     * @return true if the collaborator does not exist, false otherwise
     */
    public boolean verifyCollaboratorById(String id) {
        return collaboratorRepository.getCollaboratorById(id) == null;
    }

    /**
     * Verifies if a skill with the given name exists.
     *
     * @param skillName the name of the skill
     * @return true if the skill does not exist, false otherwise
     */
    public boolean verifySkillByName(String skillName) {
        return skillRepository.getSkillByName(skillName) == null;
    }
}