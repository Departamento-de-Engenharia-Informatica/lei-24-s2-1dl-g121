package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.List;

public class AssignSkillController {
    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;

    public AssignSkillController() {
        getCollaboratorRepository();
        getSkillRepository();
    }

    public AssignSkillController(CollaboratorRepository collaboratorRepository, SkillRepository skillRepository) {
        this.collaboratorRepository = collaboratorRepository;
        this.skillRepository = skillRepository;
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            skillRepository = Repositories.getInstance().getSkillRepository();
        }
        return skillRepository;
    }

    public List<Collaborator> getCollaboratorList(){
        return collaboratorRepository.getCollaborators();
    }

    public List<Skill> getSkillList() {
        return skillRepository.getSkills();
    }

    public void assignSkill(String ID, String skillName) {
        Collaborator collaborator = collaboratorRepository.getCollaboratorById(ID);
        Skill skill = skillRepository.getSkillByName(skillName);
        collaborator.addSkill(skill);
    }

    public boolean verifyCollaboratorById(String id) {
        return collaboratorRepository.getCollaboratorById(id) == null;
    }

    public boolean verifySkillByName(String skillName) {
        return skillRepository.getSkillByName(skillName) == null;
    }
}
