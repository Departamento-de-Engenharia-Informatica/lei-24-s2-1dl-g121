package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateTeamController {
    private OrganizationRepository organizationRepository;
    private CreateTeamRepository createTeamRepository;
    private AuthenticationRepository authenticationRepository;
    private SkillRepository skillRepository;
    private CollaboratorRepository collaboratorRepository;
    private  List<Collaborator> collaborators;

    public CreateTeamController() {
        Repositories repositories = Repositories.getInstance();
        organizationRepository = repositories.getOrganizationRepository();
        createTeamRepository = repositories.getCreateTeamRepository();
        authenticationRepository = repositories.getAuthenticationRepository();
        skillRepository = repositories.getSkillRepository();
        collaboratorRepository = repositories.getCollaboratorRepository();
    }

    // Allows receiving the repositories as parameters for testing purposes
    public CreateTeamController(OrganizationRepository organizationRepository,
                               CreateTeamRepository createTeamRepository,
                               AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.createTeamRepository = createTeamRepository;
        this.authenticationRepository = authenticationRepository;
    }
    public Optional<Team> createTeam(ArrayList<Skill> requiredSkills, int maxSize, int minSize) {
        Team newTeam = new Team(requiredSkills, maxSize, minSize);
        if (!createTeamRepository.getTeams().contains(newTeam)) {
            try {
                createTeamRepository.addTeam(newTeam);
                return Optional.of(newTeam);
            } catch (UnsupportedOperationException e) {
                System.out.println("Error:" + e.getMessage());
            }
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
