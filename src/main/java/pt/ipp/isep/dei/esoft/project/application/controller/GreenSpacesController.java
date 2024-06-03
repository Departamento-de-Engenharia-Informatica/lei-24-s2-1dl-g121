package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpacesController {
    private OrganizationRepository organizationRepository;
    private AuthenticationRepository authenticationRepository;
    private GreenSpacesRepository greenSpacesRepository;
    private ObservableList<GreenSpaces> greenSpacesList = FXCollections.observableArrayList();

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
    public Optional<GreenSpaces> createGreenSpace(String type, double area, String address, String name, String email) {
        GreenSpaces newGreenSpace = new GreenSpaces(type, area, address, name, email);
        if (!greenSpacesRepository.getGreenSpaces().contains(newGreenSpace)) {
            try {
                greenSpacesRepository.add(newGreenSpace);
                return Optional.of(newGreenSpace);
            } catch (UnsupportedOperationException e) {
                System.out.println("Error:" + e.getMessage());
            }
        }
        return Optional.empty();
    }

    public List<GreenSpaces> getGreenSpaces() {
        return greenSpacesRepository.getGreenSpaces();
    }

    public List<String> getGreenSpacesNames() {
        List<String> greenSpacesNames = new ArrayList<>();
        for (GreenSpaces greenSpace : greenSpacesRepository.getGreenSpaces()) {
            greenSpacesNames.add(greenSpace.getName());
        }
        return greenSpacesNames;
    }

    public GreenSpaces getGreenSpaceByName(String name) {
        for (GreenSpaces greenSpace : greenSpacesRepository.getGreenSpaces()) {
            if (greenSpace.getName().equals(name)) {
                return greenSpace;
            }
        }
        return null;
    }
    public ObservableList<GreenSpaces> getRuntimeGreenSpaces() {
        return greenSpacesList;
    }
}
