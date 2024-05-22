package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaces;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GreenSpacesRepository {
    private final List<GreenSpaces> greenSpaces;

    public GreenSpacesRepository() {greenSpaces = new ArrayList<>();}

    public Optional<GreenSpaces> addGreenSpace(GreenSpaces greenSpace) {
        Optional<GreenSpaces> newGreenSpace = Optional.empty();
        if (validateGreenSpace(greenSpace)) {
            newGreenSpace = Optional.of(greenSpace);
            greenSpaces.add(newGreenSpace.get());
        }
        return newGreenSpace;
    }

    private boolean validateGreenSpace(GreenSpaces greenSpace) {
        boolean isValid = !greenSpaces.contains(greenSpace);
        return isValid;
    }

    public List<GreenSpaces> getGreenSpaces() {
        return List.copyOf(greenSpaces);
    }
}
