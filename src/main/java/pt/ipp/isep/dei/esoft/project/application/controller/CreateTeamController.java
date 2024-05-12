package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Optional;

public class CreateTeamController {
    private OrganizationRepository organizationRepository;
    private CreateTeamRepository createTeamRepository;
    private AuthenticationRepository authenticationRepository;

    public CreateTeamController() {
        Repositories repositories = Repositories.getInstance();
        organizationRepository = repositories.getOrganizationRepository();
        createTeamRepository = repositories.getCreateTeamRepository();
        authenticationRepository = repositories.getAuthenticationRepository();
    }

    // Allows receiving the repositories as parameters for testing purposes
    public CreateTeamController(OrganizationRepository organizationRepository,
                               CreateTeamRepository createTeamRepository,
                               AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.createTeamRepository = createTeamRepository;
        this.authenticationRepository = authenticationRepository;
    }
    public Optional<Team> createTeam(int numSkills, ArrayList<Skill> requiredSkills) {
        Team newTeam = new Team(numSkills, requiredSkills);
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

}
