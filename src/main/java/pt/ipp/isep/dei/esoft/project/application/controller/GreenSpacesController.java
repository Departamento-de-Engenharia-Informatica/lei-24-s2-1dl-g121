package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpacesController {
    private OrganizationRepository organizationRepository;
    private AuthenticationRepository authenticationRepository;
    private GreenSpacesRepository greenSpacesRepository;

    public GreenSpacesController() {
        Repositories repositories = Repositories.getInstance();
        organizationRepository = repositories.getOrganizationRepository();
        authenticationRepository = repositories.getAuthenticationRepository();
        greenSpacesRepository = repositories.getGreenSpacesRepository();
    }

    // Allows receiving the repositories as parameters for testing purposes
    public GreenSpacesController(OrganizationRepository organizationRepository,
                               GreenSpacesRepository greenSpacesRepository,
                                AuthenticationRepository authenticationRepository) {
        this.organizationRepository = organizationRepository;
        this.authenticationRepository = authenticationRepository;
        this.greenSpacesRepository = greenSpacesRepository;
    }
    public Optional<GreenSpaces> createGreenSpace(String type, double area, String address, String name) {
        GreenSpaces newGreenSpace = new GreenSpaces(type, area, address, name);
        if (!greenSpacesRepository.getGreenSpaces().contains(newGreenSpace)) {
            try {
                greenSpacesRepository.addGreenSpace(newGreenSpace);
                return Optional.of(newGreenSpace);
            } catch (UnsupportedOperationException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
        return Optional.empty();
    }

}
