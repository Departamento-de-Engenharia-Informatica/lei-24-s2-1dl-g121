package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpacesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class GreenSpacesController {
    private GreenSpacesRepository repository;


    public GreenSpacesController() {
        this.repository = GreenSpacesRepository.getInstance();
    }

    public List<String> getGreenSpacesNamesAndEmails() {
        List<GreenSpaces> greenSpacesList = repository.getGreenSpaces();
        return greenSpacesList.stream()
                .map(gs -> gs.getName() + " | " + gs.getEmail())
                .collect(Collectors.toList());
    }

    public Optional<GreenSpaces> createGreenSpace(String type, double area, String address, String name, String email) {
        return repository.add(new GreenSpaces(type, area, address, name, email));
    }

    public List<String> getGreenSpacesNames() {
        List<String> greenSpacesNames = new ArrayList<>();
        for (GreenSpaces greenSpace : repository.getGreenSpaces()) {
            greenSpacesNames.add(greenSpace.getName());
        }
        return greenSpacesNames;
    }

    public GreenSpaces getGreenSpaceByName(String name) {
        for (GreenSpaces greenSpace : repository.getGreenSpaces()) {
            if (greenSpace.getName().equals(name)) {
                return greenSpace;
            }
        }
        return null;
    }
}

