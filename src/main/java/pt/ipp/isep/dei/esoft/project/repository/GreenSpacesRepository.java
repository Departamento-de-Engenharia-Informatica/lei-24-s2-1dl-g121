package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;

import java.util.ArrayList;
import java.util.List;

public class GreenSpacesRepository {
    private List<GreenSpaces> greenSpacesDatabase = new ArrayList<>();

    // Method to add a single GreenSpaces object
    public void addGreenSpace(GreenSpaces greenSpace) {
        greenSpacesDatabase.add(greenSpace);
    }

    // Method to add a list of GreenSpaces objects
    public void addGreenSpaces(List<GreenSpaces> greenSpaces) {
        greenSpacesDatabase.addAll(greenSpaces);
    }

    public List<GreenSpaces> getAllGreenSpaces() {
        return new ArrayList<>(greenSpacesDatabase);
    }
}

