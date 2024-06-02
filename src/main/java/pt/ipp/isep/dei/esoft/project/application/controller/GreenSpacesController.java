package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.repository.GreenSpacesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpacesController {
    private List<GreenSpaces> runtimeGreenSpaces = new ArrayList<>();
    private GreenSpacesRepository repository;

    public GreenSpacesController(GreenSpacesRepository repository) {
        this.repository = repository;
    }

    public GreenSpaces createGreenSpace(String type, double area, String address, String name, String email) {
        GreenSpaces greenSpace = new GreenSpaces(type, area, address, name, email);
        if (greenSpace.isValid()) {
            return greenSpace;
        }
        return null;
    }

    public void addRuntimeGreenSpace(GreenSpaces greenSpace) {
        runtimeGreenSpaces.add(greenSpace);
    }

    public List<GreenSpaces> getRuntimeGreenSpaces() {
        return new ArrayList<>(runtimeGreenSpaces);
    }

    public void clearRuntimeGreenSpaces() {
        runtimeGreenSpaces.clear();
    }

    public void saveGreenSpaces() {
        repository.addGreenSpaces(runtimeGreenSpaces); // Save all runtime green spaces
        clearRuntimeGreenSpaces();
    }
}
