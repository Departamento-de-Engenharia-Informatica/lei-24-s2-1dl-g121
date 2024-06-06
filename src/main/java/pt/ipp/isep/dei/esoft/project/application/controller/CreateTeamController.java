package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateTeamController {
    private TeamRepository teamRepository;
    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;

    public CreateTeamController() {
        getTeamRepository();
        getSkillRepository();
        getCollaboratorRepository();
    }

    private void getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            collaboratorRepository = Repositories.getInstance().getCollaboratorRepository();
        }
    }

    private void getSkillRepository() {
        if (skillRepository == null) {
            skillRepository = Repositories.getInstance().getSkillRepository();
        }
    }

    private void getTeamRepository() {
        if (teamRepository == null) {
            teamRepository = Repositories.getInstance().getCreateTeamRepository();
        }
    }

    // Allows receiving the repositories as parameters for testing purposes
    public CreateTeamController(TeamRepository teamRepository, SkillRepository skillRepository, CollaboratorRepository collaboratorRepository) {
        this.teamRepository = teamRepository;
        this.skillRepository = skillRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    public Optional<Team> createTeam(List<Collaborator> collaborators) {
        Team newTeam = new Team(collaborators);
        if (!teamRepository.getTeams().contains(newTeam)) {
            try {
                teamRepository.addTeam(newTeam);
            } catch (UnsupportedOperationException e) {
                System.out.println("Error:" + e.getMessage());
            }
            return Optional.of(newTeam);
        }
        return Optional.empty();
    }

    public boolean verifySkillByName(String skillName) {
        return skillRepository.getSkillByName(skillName) == null;
    }

    public List<Skill> getSkillList() {
        return skillRepository.getSkills();
    }

    public List<Collaborator> getCollaboratorsBySkills(List<Skill> skillsNeeded) {
        List<Collaborator> collaboratorsWithSkills = new ArrayList<>();

        // Iterate through all collaborators
        for (Collaborator collaborator : collaboratorRepository.getCollaborators()) {
            // Check if the collaborator possesses all the required skills
            if (collaborator.hasAllSkills(skillsNeeded)) {
                collaboratorsWithSkills.add(collaborator);
            }
        }

        return collaboratorsWithSkills;
    }

//    public Collaborator getCollaboratorByName() {
//
//        // Iterate through all collaborators
//        for (Collaborator collaborator : collaboratorRepository.getCollaborators()) {
//            // Check if the collaborator possesses all the required skills
//            if (collaborator.getName()) {
//                System.out.println(collaborator);
//            }
//        }
//
//        return getCollaboratorByName();
//    }
}
